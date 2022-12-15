create table students_courses(
    id int primary key generated always as identity,
    student_id int,
    course_id int
);

ALTER TABLE students_courses
ADD FOREIGN KEY (student_id) REFERENCES users(id);
ALTER TABLE students_courses
ADD FOREIGN KEY (course_id) REFERENCES courses(id);