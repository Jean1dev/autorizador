package org.autorizador.core.application;

import org.autorizador.account.AccountEvent;
import org.autorizador.core.api.dto.AccountCreateInput;
import org.autorizador.core.api.dto.TransactionInput;
import org.autorizador.core.api.dto.TransactionExecOutput;
import org.autorizador.core.commons.EventService;
import org.autorizador.core.exception.ApplicationException;
import org.autorizador.transaction.TransactionEvent;
import org.autorizador.useCase.ProcessEvents;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionApplication {
    private final EventService eventService;
    private final ProcessEvents processEvents;

    public TransactionApplication(EventService eventService) {
        this.eventService = eventService;
        this.processEvents = new ProcessEvents();
    }

    public List<TransactionExecOutput> process(TransactionInput input) {
        var transactionEvent = new TransactionEvent(input.merchant(), input.amount(), LocalDateTime.now());
        var result = processEvents.process(List.of(transactionEvent));

        if (result.violations().isEmpty()) {
            eventService.send(transactionEvent);
            return List.of(TransactionExecOutput.from(result));
        }

        throw new ApplicationException(result.violations());
    }

    public List<TransactionExecOutput> process(AccountCreateInput input) {
        var accountEvent = new AccountEvent(input.activeCard(), input.availableLimit());
        var result = processEvents.process(List.of(accountEvent));

        if (result.violations().isEmpty()) {
            eventService.send(accountEvent);
            return List.of(TransactionExecOutput.from(result));
        }

        throw new ApplicationException(result.violations());
    }
}
