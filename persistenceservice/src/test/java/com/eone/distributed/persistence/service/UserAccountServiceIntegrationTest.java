package com.eone.distributed.persistence.service;

import com.eone.distributed.persistence.PersistenceApplication;
import com.eone.distributed.persistence.model.SignupMessage;
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
        classes = {PersistenceApplication.class})
public class UserAccountServiceIntegrationTest {

    @Autowired
    private UserAccountService userAccountService;

    @Test
    public void testValidation_accountIsValidated() {
        SignupMessage signupMessage = new SignupMessage();
        String uuid = UUID.randomUUID().toString();
        signupMessage.setUuid(uuid);
        signupMessage.setEmail("integration-test-" + uuid + "@mail.com");
        signupMessage.setPassword("qwe123QWE$");
        userAccountService.process(signupMessage);


        boolean actual = userAccountService.isIdentified(signupMessage);

        assertTrue(actual);
    }

}
