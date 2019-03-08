CREATE DATABASE IF NOT EXISTS signup_db;

CREATE TABLE IF NOT EXISTS signup_db.signup_table (
  email              VARCHAR(254),
  uuid               VARCHAR(60),
  password_hash      BLOB,
  password_salt      BLOB,
  registration_time  TIMESTAMP,
  PRIMARY KEY (email),
  INDEX uuid_index (uuid)
  );

USE signup_db;