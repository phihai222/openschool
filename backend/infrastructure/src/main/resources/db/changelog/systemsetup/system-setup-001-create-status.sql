-- Liquibase formatted SQL
-- changeset systemsetup:001-create-status-table
CREATE TABLE system_setup_status (
    id UUID PRIMARY KEY,
    school_id UUID,
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    current_step VARCHAR(50) NOT NULL,
    admin_created BOOLEAN NOT NULL DEFAULT FALSE,
    school_created BOOLEAN NOT NULL DEFAULT FALSE,
    year_created BOOLEAN NOT NULL DEFAULT FALSE,
    grade_created BOOLEAN NOT NULL DEFAULT FALSE,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);