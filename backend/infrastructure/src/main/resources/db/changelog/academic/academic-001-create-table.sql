--liquibase formatted sql
--changeset openschool:academic-001-create-table

CREATE TABLE academic_year (
    id UUID PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE semester (
    id UUID PRIMARY KEY,
    academic_year_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT fk_academic_year FOREIGN KEY (academic_year_id) REFERENCES academic_year(id) ON DELETE CASCADE
);

