package org.tacokitchen.messaging;

import org.tacokitchen.dao.TacoOrder;

public interface OrderReceiverService {
    TacoOrder receiveOrder();
}
