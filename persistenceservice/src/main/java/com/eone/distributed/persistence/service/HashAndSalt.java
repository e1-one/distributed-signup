package com.eone.distributed.persistence.service;

public class HashAndSalt {
    private byte[] hash;
    private byte[] salt;

    public HashAndSalt() {
    }

    public HashAndSalt(byte[] hash, byte[] salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
