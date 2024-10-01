package org.autorizador.core.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.autorizador.account.AccountEvent;

public record AccountOutput(
        @JsonProperty("active-card") boolean activeCard,
        @JsonProperty("available-limit") double availableLimit
) {
    public static AccountOutput from(AccountEvent account) {
        return new AccountOutput(account.isActiveCard(), account.getAvailableLimit());
    }
}
