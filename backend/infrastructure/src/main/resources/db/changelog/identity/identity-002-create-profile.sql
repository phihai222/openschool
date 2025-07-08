--liquibase formatted sql

--changeset openschool:identity-003-create-profile
CREATE TABLE identity_profile
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    identity_id         UUID NOT NULL UNIQUE REFERENCES identity (id) ON DELETE CASCADE,
    first_name          VARCHAR(100),
    last_name           VARCHAR(100),
    birthdate           DATE,
    created_at          TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

--rollback DROP TABLE IF EXISTS identity_profile;

