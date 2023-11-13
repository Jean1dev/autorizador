package org.autorizador.useCase;

import org.autorizador.DomainEvent;
import org.autorizador.TransactionEventFactory;
import org.autorizador.transaction.TransactionEvent;
import org.autorizador.violations.ViolationDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessEventsTest {

    private ProcessEvents useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProcessEvents();
    }

    @DisplayName("Shoud be process create account succesfully")
    @Test
    void process() {
        List<DomainEvent> events = Collections.singletonList(TransactionEventFactory.accoutEvent());

        var output = useCase.process(events);

        assertTrue(output.violations().isEmpty());
        assertTrue(output.account().isActiveCard());
        assertEquals(250.0, output.account().getAvailableLimit());
    }

    @DisplayName("Shoud be return 1 violation account already created")
    @Test
    void expect1Violation() {
        List<DomainEvent> events = Collections.singletonList(TransactionEventFactory.accoutEvent());

        var output = useCase.process(events);
        output = useCase.process(events);

        assertEquals(1, output.violations().size());
        assertEquals(ViolationDefinition.ACCOUNT_ALREADY_CREATED.name(), output.violations().get(0));
        assertTrue(output.account().isActiveCard());
        assertEquals(250.0, output.account().getAvailableLimit());
    }

    @DisplayName("Shoud be return ACCOUNT_NOT_INITIALIZED")
    @Test
    void expectACCOUNT_NOT_INITIALIZED() {
        List<DomainEvent> events = Collections.singletonList(TransactionEventFactory.fromNow());

        var output = useCase.process(events);

        assertEquals(1, output.violations().size());
        assertEquals(ViolationDefinition.ACCOUNT_NOT_INITIALIZED.name(), output.violations().get(0));
        assertNull(output.account());
    }

    @DisplayName("Shoud be return CARD_NOT_ACTIVE")
    @Test
    void expectCARD_NOT_ACTIVE() {
        List<DomainEvent> events = Arrays.asList(
                TransactionEventFactory.accoutCardNotActive(),
                TransactionEventFactory.fromNow()
        );

        var output = useCase.process(events);

        assertEquals(1, output.violations().size());
        assertEquals(ViolationDefinition.CARD_NOT_ACTIVE.name(), output.violations().get(0));
        assertFalse(output.account().isActiveCard());
    }

    @DisplayName("Should be return DOUBLED_TRANSACTION")
    @Test
    void expectDOUBLED_TRANSACTION() {
        var transactionAtSameTime = TransactionEventFactory.fromNow();
        var transactionAtSameTimeDuplicate = TransactionEventFactory.fromTime(transactionAtSameTime.getTime().plusSeconds(30));

        List<DomainEvent> events = Arrays.asList(
                TransactionEventFactory.accoutEvent(),
                transactionAtSameTime,
                transactionAtSameTimeDuplicate
        );

        var output = useCase.process(events);

        assertEquals(1, output.violations().size());
        assertEquals(ViolationDefinition.DOUBLED_TRANSACTION.name(), output.violations().get(0));
        assertTrue(output.account().isActiveCard());

        transactionAtSameTime = TransactionEventFactory.fromNow();
        transactionAtSameTimeDuplicate = TransactionEventFactory.fromTime(transactionAtSameTime.getTime().plusMinutes(1));

        events = Arrays.asList(
                transactionAtSameTime,
                transactionAtSameTimeDuplicate
        );

        output = useCase.process(events);

        assertTrue(output.violations().isEmpty());
        assertTrue(output.account().isActiveCard());
    }

    @DisplayName("should be return HIGH_FREQUENCY_SMALL_INTERVAL")
    @Test
    void expectHIGH_FREQUENCY_SMALL_INTERVAL() {
        var transactionAtSameTime = TransactionEventFactory.fromNow();

        List<DomainEvent> events = Arrays.asList(
                TransactionEventFactory.accoutEvent(),
                transactionAtSameTime,
                new TransactionEvent(
                        transactionAtSameTime.getMerchant(),
                        3.50,
                        transactionAtSameTime.getTime()
                )
        );

        var output = useCase.process(events);

        assertEquals(1, output.violations().size());
        assertEquals(ViolationDefinition.HIGH_FREQUENCY_SMALL_INTERVAL.name(), output.violations().get(0));
        assertTrue(output.account().isActiveCard());
    }

    @DisplayName("shoud be return INSUFFICIENT_LIMIT")
    @Test
    void expectINSUFFICIENT_LIMIT() {
        List<DomainEvent> events = Arrays.asList(
                TransactionEventFactory.accoutEvent(),
                TransactionEventFactory.fromAmmout(260.00)
        );

        var output = useCase.process(events);

        assertEquals(1, output.violations().size());
        assertEquals(ViolationDefinition.INSUFFICIENT_LIMIT.name(), output.violations().get(0));
        assertTrue(output.account().isActiveCard());
    }
}