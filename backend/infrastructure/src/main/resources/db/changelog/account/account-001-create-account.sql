--liquibase formatted sql

--changeset openschool:account-002-update-for-multilogin
CREATE TABLE account
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    identity_id     UUID NOT NULL REFERENCES identity (id) ON DELETE CASCADE,
    account_type    VARCHAR(50) NOT NULL DEFAULT 'LOCAL',
    username        VARCHAR(255) NOT NULL UNIQUE,
    email           VARCHAR(255),
    password_hash   VARCHAR(255),
    is_active       BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

--rollback DROP TABLE IF EXISTS account;
