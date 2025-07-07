--liquibase formatted sql

--changeset openschool:identity-001-create-user-credentials
CREATE TABLE identity
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

--rollback DROP TABLE identity;