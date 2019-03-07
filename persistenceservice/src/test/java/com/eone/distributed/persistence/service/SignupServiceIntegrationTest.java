package com.eone.distributed.persistence.service;

import com.eone.distributed.persistence.Application;
import com.eone.distributed.persistence.model.SignupMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {Application.class})
public class SignupServiceIntegrationTest {

    @Autowired
    private SignupService signupService;

    @Before

    @Test
    public void testValidation_accountIsValidated() {
        SignupMessage signupMessage = new SignupMessage();
        signupMessage.setEmail("integration-test-" + UUID.randomUUID().toString() + "@mail.com");
        signupMessage.setPassword("qwe123QWE$");
        signupService.process(signupMessage);


        boolean actual = signupService.isIdentified(signupMessage);

        assertTrue(actual);
    }

}
