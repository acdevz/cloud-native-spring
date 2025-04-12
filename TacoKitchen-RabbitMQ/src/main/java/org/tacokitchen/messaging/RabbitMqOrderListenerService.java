package org.tacokitchen.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tacokitchen.dao.TacoOrder;

@Service
@Qualifier("rabbit")
public class RabbitMqOrderListenerService {
    private final KitchenUI ui;
    public RabbitMqOrderListenerService(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacocloud.orders.queue")
    public void receiveOrder(TacoOrder order) {
        ui.addOrder(order);
    }
}
