--liquibase formatted sql

--changeset openschool:department-create-database-001
CREATE TABLE department
(
    department_id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    department_name     VARCHAR(100) NOT NULL,
    description    VARCHAR(255) DEFAULT NULL,
    department_head        VARCHAR(100) DEFAULT NULL,
    department_deputy           VARCHAR(100) DEFAULT NULL,
    department_code   VARCHAR(50) NOT NULL,
    department_email       VARCHAR(100) DEFAULT NULL,
    department_phone VARCHAR(100) DEFAULT NULL,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

--rollback DROP TABLE IF EXISTS account;
