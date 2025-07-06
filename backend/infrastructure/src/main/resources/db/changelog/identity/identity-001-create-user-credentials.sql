--liquibase formatted sql

--changeset openschool:identity-001-create-user-credentials
CREATE SCHEMA IF NOT EXISTS identity;

CREATE TABLE IF NOT EXISTS identity.user_credentials
(
    id            UUID PRIMARY KEY,
    username      VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    created_at    TIMESTAMP DEFAULT NOW()
);

--rollback DROP TABLE identity.user_credentials;
--rollback DROP SCHEMA IF EXISTS identity;
