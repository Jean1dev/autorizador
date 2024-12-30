package org.autorizador.query.account.graphql;

import org.autorizador.query.account.AccountDocument;
import org.autorizador.query.account.AccountRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @QueryMapping
    public List<GqlAccount> accounts() {
        Iterator<AccountDocument> iterator = repository.findAll().iterator();
        List<GqlAccount> accounts = new ArrayList<>();
        while (iterator.hasNext()) {
            accounts.add(GqlAccount.toGqlAccount(iterator.next()));
        }
        return accounts;
    }
}
