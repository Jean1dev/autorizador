package org.autorizador.core.listener;

import org.autorizador.core.IntegrationTest;
import org.autorizador.core.config.annotations.AccountCreatedQueue;
import org.autorizador.core.config.json.Json;
import org.autorizador.core.config.properties.QueueProperties;
import org.autorizador.core.listener.messages.AccountMessage;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AccountCreatedListenerTest extends IntegrationTest {

    @Autowired
    private TestRabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitListenerTestHarness harness;

    @Autowired
    @AccountCreatedQueue
    private QueueProperties queueProperties;

    @Test
    void onEvent() throws InterruptedException {
        // given
        var accountMessage = new AccountMessage(true, 1000);
        var body = Json.writeValueAsString(accountMessage);

        // when
        rabbitTemplate.convertAndSend(queueProperties.getQueue(), body);

        // then
        var invocationData = harness.getNextInvocationDataFor("accountCreatedListener", 1, TimeUnit.SECONDS);

        assertNotNull(invocationData);
        assertNotNull(invocationData.getArguments());

        final var actualMessage = (String) invocationData.getArguments()[0];
        assertEquals(accountMessage, actualMessage);


    }
}