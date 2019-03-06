package com.eone.signup.service;

import com.eone.signup.model.SignupDto;
import com.eone.signup.model.SignupMessage;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class SignupService {


    private final Logger logger = LoggerFactory.getLogger(SignupService.class);

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @Autowired
    private PasswordValidator passwordValidator;

/*    @Value("${signup.topic}")
    private String signupTopik;*/

    public String process(SignupDto signupDto){
        if(isValidSignupInput(signupDto)){
            SignupMessage message = new SignupMessage(signupDto);
            template.send("signup-topic", message);
            return message.getTraceId();
        }
        throw new IllegalArgumentException("Signup input is not valid");
    }

    private boolean isValidSignupInput(SignupDto signupDto){
        boolean emailValid = EmailValidator.getInstance().isValid(signupDto.getEmail());
        boolean passValid = passwordValidator.validate(signupDto.getPassword());
        return emailValid && passValid;
    }
}
