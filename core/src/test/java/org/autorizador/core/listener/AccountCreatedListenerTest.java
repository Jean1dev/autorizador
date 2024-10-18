package org.autorizador.core.listener;

import org.autorizador.account.AccountEvent;
import org.autorizador.core.ListenerTest;
import org.autorizador.core.PgTestConfiguration;
import org.autorizador.core.config.annotations.AccountCreatedQueue;
import org.autorizador.core.config.json.Json;
import org.autorizador.core.config.properties.QueueProperties;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ListenerTest
class AccountCreatedListenerTest extends PgTestConfiguration {

    @Autowired
    private TestRabbitTemplate template;

    @Autowired
    private RabbitListenerTestHarness harness;

    @Autowired
    private AccountCreatedListener listener;

    @Autowired
    @AccountCreatedQueue
    private QueueProperties queueProperties;

    @Test
    void onEvent() throws InterruptedException {
        var event = new AccountEvent(true, 1000.00);
        final var expectedMessage = Json.writeValueAsString(event);

        template.convertAndSend(queueProperties.getQueue(), expectedMessage);

        final var invocationData =
                harness.getNextInvocationDataFor(AccountCreatedListener.ACCOUNT_LISTENER_ID, 1, TimeUnit.SECONDS);

        assertNotNull(invocationData);
        assertNotNull(invocationData.getArguments());

        final var actualMessage = (String) invocationData.getArguments()[0];
        assertNotNull(listener);
    }
}