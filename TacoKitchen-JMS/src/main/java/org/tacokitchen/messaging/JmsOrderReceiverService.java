package org.tacokitchen.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.tacokitchen.dao.TacoOrder;

@Service
public class JmsOrderReceiverService implements OrderReceiverService {
    private final JmsTemplate jmsTemplate;

    public JmsOrderReceiverService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public TacoOrder receiveOrder() {
        return (TacoOrder) jmsTemplate.receiveAndConvert("tacocloud.orders.queue");
    }
}
