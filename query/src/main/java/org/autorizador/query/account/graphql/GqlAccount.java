package org.autorizador.query.account.graphql;

import org.autorizador.query.account.AccountDocument;

public record GqlAccount(
        String id,
        Double account_limit,
        Boolean active
) {

    static GqlAccount toGqlAccount(AccountDocument document) {
        return new GqlAccount(document.getId(), document.getAccountLimit(), document.getActive());
    }
}
