package org.autorizador.core.commons.implementations;

import org.autorizador.core.commons.EventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;

public class RabbitMQService implements EventService {
    private final String exchange;
    private final String routingKey;
    private final RabbitOperations operations;

    public RabbitMQService(String exchange, String routingKey, RabbitOperations operations) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.operations = operations;
    }

    @Override
    public void send(Object message) {
        operations.convertAndSend(exchange, routingKey, message);
    }
}
