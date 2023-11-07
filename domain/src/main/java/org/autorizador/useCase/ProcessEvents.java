package org.autorizador.useCase;

import org.autorizador.DomainEvent;
import org.autorizador.account.AccountEvent;
import org.autorizador.transaction.TransactionEvent;
import org.autorizador.useCase.records.ProcessResultOutput;
import org.autorizador.violations.ViolationDefinition;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.autorizador.violations.Violation.buildFromEnum;

public class ProcessEvents {

    private AccountEvent accountEvent;
    private List<TransactionEvent> transactionEvents;
    private List<String> violations;

    public ProcessResultOutput process(List<DomainEvent> events) {
        violations = new ArrayList<>();
        transactionEvents = new ArrayList<>();
        events.forEach(domainEvent -> {
            if (domainEvent instanceof AccountEvent) {
                initAccount((AccountEvent) domainEvent);
                return;
            }

            if (domainEvent instanceof TransactionEvent) {
                validateTransaction((TransactionEvent) domainEvent);
                transactionEvents.add((TransactionEvent) domainEvent);
            }
        });

        return new ProcessResultOutput(accountEvent, violations);
    }

    private void validateTransaction(TransactionEvent domainEvent) {
        if (accountEvent == null) {
            violations.add(buildFromEnum(ViolationDefinition.ACCOUNT_NOT_INITIALIZED));
            return;
        }

        if (!accountEvent.isActiveCard()) {
            violations.add(buildFromEnum(ViolationDefinition.CARD_NOT_ACTIVE));
        }

        transactionEvents
                .stream()
                .filter(transactionEvent -> transactionEvent.getAmount() == domainEvent.getAmount())
                .filter(transactionEvent -> {
                    LocalDateTime transactionEventTime = transactionEvent.getTime();
                    LocalDateTime domainEventTime = domainEvent.getTime();
                    Duration between = Duration.between(domainEventTime, transactionEventTime);
                    return between.toMinutes() == 0;
                }).forEach(transactionEvent -> violations.add(buildFromEnum(ViolationDefinition.DOUBLED_TRANSACTION)));

        transactionEvents
                .stream()
                .filter(transactionEvent -> transactionEvent.getTime().isEqual(domainEvent.getTime()))
                .forEach(transactionEvent -> violations.add(buildFromEnum(ViolationDefinition.HIGH_FREQUENCY_SMALL_INTERVAL)));

        if (accountEvent.getAvailableLimit() < domainEvent.getAmount()) {
            violations.add(buildFromEnum(ViolationDefinition.INSUFFICIENT_LIMIT));
        }
    }

    private void initAccount(AccountEvent domainEvent) {
        if (accountEvent == null) {
            accountEvent = new AccountEvent(domainEvent.isActiveCard(), domainEvent.getAvailableLimit());
            return;
        }

        violations.add(buildFromEnum(ViolationDefinition.ACCOUNT_ALREADY_CREATED));
    }
}
