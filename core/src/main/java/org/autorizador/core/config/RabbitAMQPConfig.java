package org.autorizador.core.config;

import org.autorizador.core.config.annotations.AccountCreatedQueue;
import org.autorizador.core.config.annotations.TransactionEventQueue;
import org.autorizador.core.config.properties.QueueProperties;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitAMQPConfig {

    @Bean
    @ConfigurationProperties("amqp.queues.account-created")
    @AccountCreatedQueue
    public QueueProperties accountCreatedQueueProperties() {
        return new QueueProperties();
    }

    @Bean
    @ConfigurationProperties("amqp.queues.transaction-event")
    @TransactionEventQueue
    public QueueProperties transactionEventQueueProperties() {
        return new QueueProperties();
    }

    @Configuration
    static class BrokerConfiguration {

        @Bean
        Exchange exchange(@AccountCreatedQueue QueueProperties properties) {
            return new DirectExchange(properties.getExchange());
        }

        @Bean
        @AccountCreatedQueue
        Queue accountCreatedQueue(@AccountCreatedQueue QueueProperties properties) {
            return new Queue(properties.getQueue());
        }

        @Bean
        Binding accountCreatedBinding(
                DirectExchange directExchange,
                @AccountCreatedQueue Queue queue,
                @AccountCreatedQueue QueueProperties properties) {

            return BindingBuilder.bind(queue)
                    .to(directExchange)
                    .with(properties.getRoutingKey());
        }


        // TRANSACTION EVENT

        @Bean
        @TransactionEventQueue
        Queue transacationEventQueue(@TransactionEventQueue QueueProperties properties) {
            return new Queue(properties.getQueue());
        }

        @Bean
        Binding transacationEventBinding(
                DirectExchange directExchange,
                @TransactionEventQueue Queue queue,
                @TransactionEventQueue QueueProperties properties) {

            return BindingBuilder.bind(queue)
                    .to(directExchange)
                    .with(properties.getRoutingKey());
        }

    }
}
