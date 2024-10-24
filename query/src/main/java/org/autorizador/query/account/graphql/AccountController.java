package org.autorizador.query.account.graphql;

import org.autorizador.query.account.AccountRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {
    private final AccountRepository repository;

    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public GqlAccount account(@Argument final String id) {
        return repository.findById(id)
                .map(GqlAccount::toGqlAccount)
                .orElse(null);
    }
}
