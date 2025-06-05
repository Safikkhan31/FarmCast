CREATE DATABASE IF NOT EXISTS farmcastdb;
USE farmcastdb;

-- Table: locations
CREATE TABLE IF NOT EXISTS locations (
    address VARCHAR(255) PRIMARY KEY,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    weather_json JSON,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: people
CREATE TABLE IF NOT EXISTS people (
    phone_number VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    FOREIGN KEY (address) REFERENCES locations(address)
);
