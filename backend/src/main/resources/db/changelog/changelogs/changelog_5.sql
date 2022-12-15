
ALTER TABLE courses
ADD COLUMN teacher_id int;

ALTER TABLE marks ALTER COLUMN mark TYPE float;
--5,6,7
INSERT INTO users (email,name,surname, password) VALUES
	 ('student1@mail.com', 'Zygmunt', 'Student1', '$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy'),
	 ('student1@mail.com', 'Tobiasz', 'Student2', '$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy'),
     ('teacher1@mail.com', 'Rafał', 'Teacher1', '$2a$10$2FZo7VtxA3h6Xe5MJtkg1O80MSJLIUTJmy.h4sLk2TOSsV8DR2iDy');

--1
INSERT INTO courses (name, teacher_id) VALUES ('Biologia', 3);

INSERT INTO students_courses (student_id, course_id) VALUES (1, 1), (2,1);

INSERT INTO marks (teacher_id, student_id, course_id, mark) VALUES (3, 1, 1, 4), (3, 1, 1, 4.5), (3, 2, 1, 4.5);