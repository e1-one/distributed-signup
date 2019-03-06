package com.eone.distributed.persistence.config;

import com.eone.distributed.persistence.model.SignupMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @KafkaListener(id = "signupGroup", topics = "signupMessage-topic")
    public void listen(SignupMessage signupMessage) {
        logger.info("Received: " + signupMessage);
    }

    @KafkaListener(id = "signUpDltGroup", topics = "signup-topic.DLT")
    public void dltListen(String in) {
        logger.info("Received from DLT: " + in);
    }

}
