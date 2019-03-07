package com.eone.distributed.persistence.dao;

import com.eone.distributed.persistence.model.SignupRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDao {

    
    private JdbcTemplate jdbcTemplate;

    public SignupDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //    @PostConstruct
//    public void doInit(){
//        String createDB = "CREATE DATABASE IF NOT EXISTS signup-db;";
//        String createTable ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME
//                + "  (email              VARCHAR(254),"
//                + "   password_hash           BLOB,"
//                + "   registration_time  TIMESTAMP)";
//        jdbcTemplate.update(createDB);
//        jdbcTemplate.update(createTable);
//    }

    public void save(SignupRecord signupRecord) {
        jdbcTemplate.update("INSERT INTO signup_db.signup_table (email, password_hash, password_salt, registration_time ) VALUES (?, ?, ?, ?);",
                signupRecord.getEmail(), signupRecord.getPasswordHash(), signupRecord.getPasswordSalt(), signupRecord.getTimestamp());
    }

    public SignupRecord get(String email) {
        return (SignupRecord) jdbcTemplate.queryForObject(
                "SELECT email, password_hash, password_salt, registration_time from signup_db.signup_table where signup_table.email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper(SignupRecord.class));
    }

}
