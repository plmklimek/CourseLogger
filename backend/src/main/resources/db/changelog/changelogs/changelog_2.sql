--liquibase formatted sql
--changeset author:Mateusz-Klimek
ALTER TABLE users
ADD COLUMN name varchar(255),
ADD COLUMN surname varchar(255);