--liquibase formatted sql
--changeset openschool:school-001-create-table
CREATE TABLE school (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(50),
    email VARCHAR(255),
    website VARCHAR(255),
    default_language VARCHAR(10),
    timezone VARCHAR(50)
);

