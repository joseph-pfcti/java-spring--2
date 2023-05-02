package com.pfcti.springjms.pubsub.subscriber;

import com.pfcti.springdata.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ExecutiveNotificationProcessor {

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumeExecutiveMessages (Message<AccountDto> message) {
        AccountDto accountDto = message.getPayload();
        log.info("consumeExecutiveMessages {}", accountDto);
        return MessageBuilder.withPayload("Message received by consumeExecutiveMessages").build();
    }
}
