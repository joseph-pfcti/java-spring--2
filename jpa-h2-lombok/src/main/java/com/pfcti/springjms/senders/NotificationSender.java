package com.pfcti.springjms.senders;

import com.pfcti.springjms.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendSMS(NotificationDto notificationDto) {
        log.info("Sending sms to the queue {}", notificationDto);
        jmsTemplate.convertAndSend("smsReceiverJms", notificationDto);
    }
}
