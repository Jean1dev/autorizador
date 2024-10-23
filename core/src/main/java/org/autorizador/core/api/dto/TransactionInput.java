package org.autorizador.core.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionInput(
        @JsonProperty("merchant" ) String merchant,
        @JsonProperty("amount" ) double amount
) {
}
