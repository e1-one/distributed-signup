package com.eone.distributed.persistence.service;

import com.eone.distributed.persistence.dao.SignupDao;
import com.eone.distributed.persistence.model.SignupMessage;
import com.eone.distributed.persistence.model.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserAccountService {

    private final Logger logger = LoggerFactory.getLogger(UserAccountService.class);

    @Autowired
    private SignupDao signupDao;
    @Autowired
    private PasswordService passwordService;

    public void process(SignupMessage signupMessage){
        logger.info("Message with trace_id:{} is in processing", signupMessage.getTraceId());

        UserAccount userAccount = new UserAccount();

        userAccount.setEmail(signupMessage.getEmail());

        HashAndSalt hashAndSalt = passwordService.hash(signupMessage.getPassword());
        userAccount.setPasswordHash(hashAndSalt.getHash());
        userAccount.setPasswordSalt(hashAndSalt.getSalt());

        userAccount.setTimestamp(new Timestamp(new Date().getTime()));

        signupDao.save(userAccount);
    }

    public boolean isIdentified(SignupMessage signupMessage){
        UserAccount userAccount = signupDao.get(signupMessage.getEmail());
        HashAndSalt hashAndSalt = new HashAndSalt(userAccount.getPasswordHash(), userAccount.getPasswordSalt());

        return passwordService.validate(
                signupMessage.getPassword(),
                hashAndSalt);
    }
}
