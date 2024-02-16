package org.autorizador.core.config;

import org.autorizador.core.commons.EventService;
import org.autorizador.core.commons.implementations.LocalEventService;
import org.autorizador.core.commons.implementations.RabbitMQService;
import org.autorizador.core.config.annotations.AccountCreatedQueue;
import org.autorizador.core.config.properties.QueueProperties;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EventServiceConfig {

    @Bean
    @Profile("dev")
    EventService localEventService() {
        return new LocalEventService();
    }

    @Bean
    @ConditionalOnMissingBean
    EventService rabbitMQService(
            final RabbitOperations operations,
            @AccountCreatedQueue QueueProperties properties
    ) {
        return new RabbitMQService(properties.getExchange(), properties.getRoutingKey(), operations);
    }

}
