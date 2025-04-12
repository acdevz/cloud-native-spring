package org.tacos.messaging;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.tacos.models.TacoOrder;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jmsTemplate;

    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
//        jmsTemplate.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                Message message = session.createObjectMessage(order);
//                message.setStringProperty("orderType", "WEB-STORE");
//                return message;
//            }
//        });

//        jmsTemplate.send(session -> {
//            Message message = session.createObjectMessage(order);
//            message.setStringProperty("orderType", "WEB-STORE");
//            return message;
//        });

//        jmsTemplate.send("tacocloud.orders.queue", session -> session.createObjectMessage(order));

        jmsTemplate.convertAndSend("tacocloud.orders.queue", order, message -> {
            message.setStringProperty("orderType", "WEB-STORE");
            return message;
        });
    }
}
