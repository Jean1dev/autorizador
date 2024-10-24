package org.autorizador.query.account;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AccountRepository extends ElasticsearchRepository<AccountDocument, String> {
}
