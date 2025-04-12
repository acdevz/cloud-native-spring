package org.tacos.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.tacos.models.TacoOrder;

@Service
public class RabbitMqOrderMessagingService implements OrderMessagingService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        /* check application configuration */
        rabbitTemplate.convertAndSend(order, message -> {
            message.getMessageProperties().setHeader("ORDER_SOURCE", "WEB-STORE");
            return message;
        });
    }
}
