package com.eone.signup.model;

import java.util.UUID;

public class SignupMessage {

    private String email;
    private String password;
    private String traceId = UUID.randomUUID().toString();

    public SignupMessage() {
        super();
    }

    public SignupMessage(SignupDto signupDto){
        this.email = signupDto.getEmail();
        this.password = signupDto.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "SignupMessage{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", traceId='" + traceId + '\'' +
                '}';
    }
}
