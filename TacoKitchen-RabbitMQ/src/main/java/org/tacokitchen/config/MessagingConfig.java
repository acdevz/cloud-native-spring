package org.tacokitchen.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Bean
    public Jackson2JsonMessageConverter messageRabbitConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue tacoOrderQueue() {
        return new Queue("tacocloud.orders.queue", true);
    }
}
