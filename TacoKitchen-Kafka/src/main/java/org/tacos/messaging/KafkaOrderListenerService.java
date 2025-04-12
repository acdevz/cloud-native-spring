package org.tacos.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tacos.models.TacoOrder;

@Service
public class KafkaOrderListenerService{
    private final KitchenUI ui;
    public KafkaOrderListenerService(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void receiveOrder(TacoOrder order) {
        ui.addOrder(order);
    }
}
