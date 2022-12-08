--liquibase formatted sql
--changeset author:Mateusz-Klimek
create table users (
    id int primary key generated always as identity,
    email varchar(255),
    password varchar(255),
    image varchar(255)
);
create table authorities (
    id int primary key generated always as identity,
    user_id int,
    authority varchar(50) not null
);

ALTER TABLE authorities
ADD FOREIGN KEY (user_id) REFERENCES users(id);

create table courses(
    id int primary key generated always as identity,
    name varchar(50) not null
);

create table marks(
    id int primary key generated always as identity,
    teacher_id int,
    student_id int,
    course_id int,
    mark int
);

ALTER TABLE marks
ADD FOREIGN KEY (teacher_id) REFERENCES users(id);
ALTER TABLE marks
ADD FOREIGN KEY (student_id) REFERENCES users(id);
ALTER TABLE marks
ADD FOREIGN KEY (course_id) REFERENCES courses(id);