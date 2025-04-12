package org.tacos.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue tacoOrderQueue() {
        return new Queue("tacocloud.orders.queue", true);
    }

    @Bean
    public DirectExchange tacoOrderExchange() {
        return new DirectExchange("tacocloud.orders.exchange", true, false);
    }

    @Bean
    public Binding tacoOrderBinding(Queue tacoOrderQueue, DirectExchange tacoOrderExchange) {
        return BindingBuilder.bind(tacoOrderQueue)
                .to(tacoOrderExchange)
                .with("tacocloud.orders.routing.key");
    }
}