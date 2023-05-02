package com.pfcti.springjms.receivers;

import com.pfcti.springjms.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationProcessor {


    /**
     * smsReceiverJms, sera el nombre de la cola donde esta escuchando
     * @param notificationDto
     */
    @JmsListener(destination = "smsReceiverJms")
    public void processMessage (NotificationDto notificationDto) {
        log.info("Receiver sms info {}", notificationDto);
    }
}
