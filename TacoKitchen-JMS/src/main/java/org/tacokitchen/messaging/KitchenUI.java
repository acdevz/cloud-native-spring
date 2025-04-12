package org.tacokitchen.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tacokitchen.dao.TacoOrder;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Service
public class KitchenUI {
    private final Queue<TacoOrder> orderQueue;

    public KitchenUI() {
        this.orderQueue = new LinkedList<>();
    }

    public void addOrder(TacoOrder order) {
        orderQueue.add(order);
        log.info("Order added to queue: {}", order);
    }

    public TacoOrder getOrder() {
        TacoOrder order = orderQueue.poll();
        // Logic to display the order in the kitchen UI
        log.info("New order received: {}", order);
        return order;
    }

    public int getPendingOrdersCount() {
        return orderQueue.size();
    }
}
