--liquibase formatted sql
--changeset openschool:insert-admin-role
INSERT INTO role (name) VALUES ('ADMIN');