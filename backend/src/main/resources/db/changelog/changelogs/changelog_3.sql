--liquibase formatted sql
--changeset author:Mateusz-Klimek

--$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy

INSERT INTO users (email,name,surname, password) VALUES
	 ('mail1@mail.com', 'Adam', 'Nowak', '$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy'),
	 ('mail2@mail.com', 'Tomasz', 'Nowicki', '$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy'),
     ('mail3@mail.com', 'Paweł', 'Lewandowski', '$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy'),
     ('mail4@mail.com', 'Rafał', 'Adamiak', '$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy');

INSERT INTO authorities (user_id,authority) VALUES
    (1,'ADMIN'),
    (2,'STUDENT'),
    (3,'TEACHER'),
    (4,'TEACHER');