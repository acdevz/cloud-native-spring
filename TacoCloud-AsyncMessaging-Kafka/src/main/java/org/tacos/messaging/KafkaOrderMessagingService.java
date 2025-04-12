package org.tacos.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tacos.models.TacoOrder;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
    private final KafkaTemplate<String, TacoOrder> kafkaTemplate;

    public KafkaOrderMessagingService(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        /* topic and payload */
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }
}
