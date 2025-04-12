package org.tacokitchen.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tacokitchen.dao.TacoOrder;

@Service
@Qualifier("rabbit")
public class RabbitMqOrderReceiverService implements OrderReceiverService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqOrderReceiverService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public TacoOrder receiveOrder() {
        return (TacoOrder) rabbitTemplate.receiveAndConvert("tacocloud.orders.queue");
    }
}
