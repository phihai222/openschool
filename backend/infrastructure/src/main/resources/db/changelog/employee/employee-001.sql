--liquibase formatted sql

--changeset openschool:employee-create-database-001
CREATE TABLE employee
(
    employeeId  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    firstName     VARCHAR(100) NOT NULL,
    lastName    VARCHAR(100) DEFAULT NULL,
    email        VARCHAR(100) DEFAULT NULL,
    phoneNumber           VARCHAR(100) DEFAULT NULL,
    department   VARCHAR(255) NOT NULL,
    position       VARCHAR(100) DEFAULT NULL,
    employeeType VARCHAR(100) DEFAULT NULL,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

--rollback DROP TABLE IF EXISTS employee;
