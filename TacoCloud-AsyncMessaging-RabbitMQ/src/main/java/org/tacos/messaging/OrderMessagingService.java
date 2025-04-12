package org.tacos.messaging;

import org.tacos.models.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
