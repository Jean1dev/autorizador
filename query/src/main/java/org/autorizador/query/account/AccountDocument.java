package org.autorizador.query.account;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "account")
public class AccountDocument {

    @Id
    private String id;

    @Field(name = "account_limit", type = FieldType.Double)
    private Double accountLimit;

    @Field(name = "account_active", type = FieldType.Boolean)
    private Boolean active;

    public AccountDocument(String id, Double accountLimit, Boolean active) {
        this.id = id;
        this.accountLimit = accountLimit;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public Double getAccountLimit() {
        return accountLimit;
    }

    public Boolean getActive() {
        return active;
    }
}
