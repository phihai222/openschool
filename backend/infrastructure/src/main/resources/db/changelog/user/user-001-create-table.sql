--liquibase formatted sql

--changeset openschool:user-001-create-users-table
CREATE TABLE IF NOT EXISTS users
(
    id    UUID PRIMARY KEY,
    email VARCHAR(255),
    name  VARCHAR(255)
);

--rollback DROP TABLE users;