package com.eone.distributed.persistence.model;

public class SignupMessage {

    private String email;
    private String password;
    private String traceId;

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
