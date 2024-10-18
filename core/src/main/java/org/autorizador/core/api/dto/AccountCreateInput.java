package org.autorizador.core.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountCreateInput(
        @JsonProperty("active-card") boolean activeCard,
        @JsonProperty("available-limit") double availableLimit
) {
}
