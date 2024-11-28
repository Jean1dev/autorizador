package org.autorizador.core.listener.messages;

public record AccountMessage(
        boolean activeCard,
        double availableLimit
) {
}
