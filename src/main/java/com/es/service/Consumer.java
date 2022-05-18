package com.es.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

@MessageDriven(mappedName = "jms/dest", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Consumer implements MessageListener {
    @EJB
    private MsgService msgService;

    @Override
    public synchronized void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            msgService.addMessage(textMessage.getText());
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }
}
