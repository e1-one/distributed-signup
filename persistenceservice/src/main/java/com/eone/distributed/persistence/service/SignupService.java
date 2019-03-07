package com.eone.distributed.persistence.service;

import com.eone.distributed.persistence.config.KafkaConfig;
import com.eone.distributed.persistence.dao.SignupDao;
import com.eone.distributed.persistence.model.SignupMessage;
import com.eone.distributed.persistence.model.SignupRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class SignupService {

    private final Logger logger = LoggerFactory.getLogger(SignupService.class);

    @Autowired
    private SignupDao signupDao;
    @Autowired
    private PasswordService passwordService;

    public void process(SignupMessage signupMessage){
        logger.info("Message {} is in processing", signupMessage.getTraceId());

        SignupRecord signupRecord = new SignupRecord();

        signupRecord.setEmail(signupMessage.getEmail());

        HashAndSalt hashAndSalt = passwordService.hash(signupMessage.getPassword());
        signupRecord.setPasswordHash(hashAndSalt.getHash());
        signupRecord.setPasswordSalt(hashAndSalt.getSalt());

        signupRecord.setTimestamp(new Timestamp(new Date().getTime()));

        signupDao.save(signupRecord);
    }

    public boolean isIdentified(SignupMessage signupMessage){
        SignupRecord signupRecord = signupDao.get(signupMessage.getEmail());
        HashAndSalt hashAndSalt = new HashAndSalt(signupRecord.getPasswordHash(), signupRecord.getPasswordSalt());

        return passwordService.validate(
                signupMessage.getPassword(),
                hashAndSalt);
    }
}
