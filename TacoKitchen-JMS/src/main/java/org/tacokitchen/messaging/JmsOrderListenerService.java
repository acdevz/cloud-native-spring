package org.tacokitchen.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.tacokitchen.dao.TacoOrder;

@Service
public class JmsOrderListenerService {
    private final KitchenUI ui;
    public JmsOrderListenerService(KitchenUI ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "tacocloud.orders.queue")
    public void receiveOrder(TacoOrder order) {
        ui.addOrder(order);
    }
}
