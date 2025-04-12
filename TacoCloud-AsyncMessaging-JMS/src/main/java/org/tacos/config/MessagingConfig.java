package org.tacos.config;

import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.tacos.models.TacoOrder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {
    // Destination is a JMS interface that represents a target to which messages can be sent
    @Bean
    Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.orders.queue");
    }

//    Message Converter
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("TacoOrder", TacoOrder.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }
}

