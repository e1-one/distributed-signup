package com.eone.distributed.test;

import com.eone.distributed.test.config.TestsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {TestsApplication.class})
public class FunctionalTest {

    @Value("${signup.url}")
    private String signupUrl;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCase1_makeSignup() {
        String randomEmailForTest = "integration-test-" + UUID.randomUUID().toString() + "@mail.com";
        String requestJson =
                "{" +
                        "\"email\":\""+randomEmailForTest+"\"," +
                        "\"password\":\"qwertyQwerty123#\"" +
                        '}';

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        Map<String, String> answer = restTemplate.postForObject(signupUrl, entity, Map.class);

        String uuid = answer.get("uuid");
        assertTrue("Uuid must be returned for a new signup",uuid.length() > 0);

        //wait 3 secs for async operations
        sleep(3);

        String actualEmail = jdbcTemplate.queryForObject("select email from signup_db.signup_table where uuid = ?", String.class, uuid);
        assertThat(actualEmail, is(randomEmailForTest));
    }

    private void sleep(int sec) {
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
