package org.autorizador.useCase;

import org.autorizador.DomainEvent;
import org.autorizador.account.AccountEvent;
import org.autorizador.violations.ViolationDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessEventsTest {

    private ProcessEvents useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProcessEvents();
    }

    @DisplayName("Shoud be process create account succesfully")
    @Test
    void process() {
        List<DomainEvent> events = Collections.singletonList(new AccountEvent(true, 250.0));

        var output = useCase.process(events);

        assertTrue(output.violations().isEmpty());
        assertTrue(output.account().isActiveCard());
        assertEquals(250.0, output.account().getAvailableLimit());
    }

    @DisplayName("Shoud be return 1 violation account already created")
    @Test
    void expect1Violation() {
        List<DomainEvent> events = Collections.singletonList(new AccountEvent(true, 250.0));

        var output = useCase.process(events);
        output = useCase.process(events);

        assertEquals(1, output.violations().size());
        assertEquals(ViolationDefinition.ACCOUNT_ALREADY_CREATED.name(), output.violations().get(0));
        assertTrue(output.account().isActiveCard());
        assertEquals(250.0, output.account().getAvailableLimit());
    }
}