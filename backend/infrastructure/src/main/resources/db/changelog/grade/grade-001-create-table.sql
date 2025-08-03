-- liquibase formatted sql
-- changeset openschool:grade-001-create-table
CREATE TABLE grade (
    id UUID PRIMARY KEY,
    school_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL UNIQUE,
    level VARCHAR(50) NOT NULL,
    min_age INTEGER,
    max_age INTEGER,
    display_order INTEGER,
    allow_class BOOLEAN,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_grade_school FOREIGN KEY (school_id) REFERENCES school(id)
);

