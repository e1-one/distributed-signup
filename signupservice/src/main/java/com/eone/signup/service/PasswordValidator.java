package com.eone.signup.service;

import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidator {

    private Pattern pattern;

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    public PasswordValidator(){
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    /**
     * <i>Password that match:<i/>
     * <br> mkyong1A@
     * <br> mkYOn12$
     *
     * <br><i>Password that doesn’t match:<i/>
     * <p> mY1A@ , too short, minimum 6 characters
     * <br> mkyong12@ , uppercase characters is required
     * <br> mkyoNg12* , special symbol “*” is not allow here
     * <br> mkyonG$$, digit is required
     * <br> MKYONG12$ , lower case character is required
     * @param password true if valid
     * @return
     */
    public boolean validate(final String password){

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }
}
