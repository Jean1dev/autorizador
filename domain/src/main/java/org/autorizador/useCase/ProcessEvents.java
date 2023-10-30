package org.autorizador.useCase;

import org.autorizador.DomainEvent;
import org.autorizador.account.AccountEvent;
import org.autorizador.useCase.records.ProcessResultOutput;
import org.autorizador.violations.ViolationDefinition;

import java.util.ArrayList;
import java.util.List;

import static org.autorizador.violations.Violation.buildFromEnum;

public class ProcessEvents {

    private AccountEvent accountEvent;
    private List<String> violations;

    public ProcessResultOutput process(List<DomainEvent> events) {
        violations = new ArrayList<>();
        events.forEach(domainEvent -> {
            if (domainEvent instanceof AccountEvent) {
                initAccount((AccountEvent) domainEvent);
            }
        });
        return null;
    }

    private void initAccount(AccountEvent domainEvent) {
        if (accountEvent == null) {
            accountEvent = new AccountEvent(accountEvent.isActiveCard(), accountEvent.getAvailableLimit());
            return;
        }

        violations.add(buildFromEnum(ViolationDefinition.ACCOUNT_ALREADY_CREATED));
    }
}
