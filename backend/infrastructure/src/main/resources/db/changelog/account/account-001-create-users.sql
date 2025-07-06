--liquibase formatted sql

--changeset openschool:account-001-create-users
CREATE SCHEMA IF NOT EXISTS account;

CREATE TABLE IF NOT EXISTS account.users
(
    id         UUID PRIMARY KEY,
    email      VARCHAR(255) UNIQUE,
    full_name  VARCHAR(255),
    role       VARCHAR(50),
    created_at TIMESTAMP DEFAULT NOW()
);

--rollback DROP TABLE IF EXISTS account.users;
--rollback DROP SCHEMA IF EXISTS account;