package com.eone.distributed.persistence.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.validation.constraints.AssertFalse;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PasswordServiceTest {

    @Test
    public void hash_outputIsDifferent() throws NoSuchAlgorithmException {
        PasswordService ps = new PasswordService();
        HashAndSalt password1 = ps.hash("password");
        HashAndSalt password2 = ps.hash("password");

        assertFalse("Hashing is working correctly", Arrays.equals(password1.getHash(), password2.getHash()));
    }

    @Test
    public void validate_twoTheSamePasswords() throws NoSuchAlgorithmException {
        PasswordService ps = new PasswordService();
        HashAndSalt password1 = ps.hash("password");

        assertTrue(ps.validate("password", password1));
    }
}