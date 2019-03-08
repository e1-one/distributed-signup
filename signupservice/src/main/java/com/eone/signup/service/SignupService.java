package com.eone.signup.service;

import com.eone.signup.model.SignupDto;
import com.eone.signup.model.SignupMessage;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SignupService {


    private final Logger logger = LoggerFactory.getLogger(SignupService.class);

    @Value("${signup.topic}")
    private String signupTopik;

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @Autowired
    private PasswordValidator passwordValidator;


    public String process(SignupDto signupDto){
        if(isValidSignupInput(signupDto)){
            SignupMessage message = new SignupMessage(signupDto);
            template.send(signupTopik, message);
            return message.getUuid();
        }
        throw new IllegalArgumentException("Signup input is not valid");
    }

    private boolean isValidSignupInput(SignupDto signupDto){
        boolean emailValid = EmailValidator.getInstance().isValid(signupDto.getEmail());
        boolean passValid = passwordValidator.validate(signupDto.getPassword());
        return emailValid && passValid;
    }

}
