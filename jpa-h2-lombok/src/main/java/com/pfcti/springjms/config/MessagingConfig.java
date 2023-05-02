package com.pfcti.springjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class MessagingConfig {

    /**
     * Serialize like sms message - Point to Point
     * @return
     */
    @Bean
    public MessageConverter jacksonHmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    /**
     * Publish / Subscribe
     * @return
     */

    @Bean(name = "pubSubNotification")
    public PublishSubscribeChannel channel () {
        return new PublishSubscribeChannel();
    }
}
