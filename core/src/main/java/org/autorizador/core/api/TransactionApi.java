package org.autorizador.core.api;

import org.autorizador.core.api.docs.TransactionApiDocs;
import org.autorizador.core.api.dto.AccountCreateInput;
import org.autorizador.core.api.dto.TransactionInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TransactionApi.PATH)
public class TransactionApi implements TransactionApiDocs {

    public static final String PATH = "/v1/transaction";

    @PostMapping
    @Override
    public ResponseEntity<Void> processTransaction(TransactionInput input) {
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/account-create")
    @Override
    public ResponseEntity<Void> processAccountCreate(AccountCreateInput input) {
        return null;
    }
}
