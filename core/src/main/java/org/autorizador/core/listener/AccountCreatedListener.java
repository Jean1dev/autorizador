package org.autorizador.core.listener;

import org.autorizador.account.AccountEvent;
import org.autorizador.core.config.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AccountCreatedListener {

    private static final Logger log = LoggerFactory.getLogger(AccountCreatedListener.class);

    @RabbitListener(queues = "${amqp.queues.account-created.queue}")
    public void onEvent(@Payload final String body) {
        var accountEvent = Json.readValue(body, AccountEvent.class);
        log.info("Account created: {}", accountEvent);


    }
}
