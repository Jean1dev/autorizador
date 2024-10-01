package org.autorizador.core.api;

import org.autorizador.core.api.docs.TransactionApiDocs;
import org.autorizador.core.api.dto.AccountCreateInput;
import org.autorizador.core.api.dto.TransactionInput;
import org.autorizador.core.application.TransactionApplication;
import org.autorizador.core.api.dto.TransactionExecOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(TransactionApi.PATH)
public class TransactionApi implements TransactionApiDocs {

    public static final String PATH = "/v1/transaction";
    private final TransactionApplication application;

    public TransactionApi(TransactionApplication application) {
        this.application = application;
    }

    @PostMapping
    @Override
    public List<TransactionExecOutput> processTransaction(TransactionInput input) {
        return application.process(input);
    }

    @PostMapping("/account-create")
    @Override
    public List<TransactionExecOutput> processAccountCreate(AccountCreateInput input) {
        return application.process(input);
    }
}
