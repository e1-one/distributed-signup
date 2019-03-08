package com.eone.distributed.persistence.dao;

import com.eone.distributed.persistence.model.UserAccount;
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

    public void save(UserAccount userAccount) {
        jdbcTemplate.update("INSERT INTO signup_db.signup_table (email, uuid, password_hash, password_salt, registration_time ) VALUES (?, ?, ?, ?, ?);",
                userAccount.getEmail(), userAccount.getUuid(), userAccount.getPasswordHash(), userAccount.getPasswordSalt(), userAccount.getTimestamp());
    }

    public UserAccount get(String email) {
        return (UserAccount) jdbcTemplate.queryForObject(
                "SELECT email, password_hash, password_salt, registration_time from signup_db.signup_table where signup_table.email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper(UserAccount.class));
    }

}
