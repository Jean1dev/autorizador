package org.autorizador.core.listener;

import org.autorizador.core.application.SaveAccountApplication;
import org.autorizador.core.config.json.Json;
import org.autorizador.core.listener.messages.AccountMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AccountCreatedListener {

    public static final String ACCOUNT_LISTENER_ID = "ACCOUNT_LISTENER_ID";

    private static final Logger log = LoggerFactory.getLogger(AccountCreatedListener.class);
    private final SaveAccountApplication saveAccountApplication;

    public AccountCreatedListener(SaveAccountApplication saveAccountApplication) {
        this.saveAccountApplication = saveAccountApplication;
    }

    @RabbitListener(id = ACCOUNT_LISTENER_ID,queues = "${amqp.queues.account-created.queue}")
    public void onEvent(@Payload final String body) {
        try {
            var accountEvent = Json.readValue(body, AccountMessage.class);
            log.info("Account created: {}", accountEvent);

            saveAccountApplication.saveAccount(accountEvent.activeCard(), accountEvent.availableLimit());
        } catch (Exception e) {
            log.error("Error processing account created event: {}", body, e);
            // Handle the error appropriately, e.g., send to a dead-letter queue or log for manual intervention
        }
    }
}
