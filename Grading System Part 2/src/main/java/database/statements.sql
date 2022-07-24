-- drop database students_grading_system if exists
-- DROP DATABASE IF exists students_grading_system;

-- create a database if not exists called Students grading system
-- CREATE DATABASE students_grading_system;

-- drop table marks if exists
DROP TABLE IF EXISTS marks;

-- drop students if exists
DROP TABLE IF EXISTS students;

-- drop table courses if exists
DROP TABLE IF EXISTS courses;

-- create a table Students with student_id as the primary key , unique student_email , student_password , student_name and student_gender
CREATE TABLE IF NOT EXISTS students
(
    student_id
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    student_email
    VARCHAR
(
    255
) NOT NULL UNIQUE,
    student_password VARCHAR
(
    255
) NOT NULL,
    student_name VARCHAR
(
    255
) NOT NULL,
    student_gender VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    student_id
)
    );

-- create a table courses with course_id as the primary key , unique course_name
CREATE TABLE IF NOT EXISTS courses
(
    course_id
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    course_name
    VARCHAR
(
    255
) NOT NULL UNIQUE,
    PRIMARY KEY
(
    course_id
)
    );

-- create a table marks with mark_id as the primary key , unique student_id , course_id and mark
CREATE TABLE IF NOT EXISTS marks
(
    mark_id
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    student_id
    INT
    NOT
    NULL,
    course_id
    INT
    NOT
    NULL,
    mark
    INT
    NOT
    NULL,
    PRIMARY
    KEY
(
    mark_id
),
    FOREIGN KEY
(
    student_id
) REFERENCES students
(
    student_id
),
    FOREIGN KEY
(
    course_id
) REFERENCES courses
(
    course_id
)
    );

-- insert students into the students table Essam , Alaa' , Yusra , Amjad , Sano , Jawad and Alaa'
INSERT INTO students(student_email, student_password, student_name, student_gender)
VALUES ('alaa1@lr.com', '123', "Alaa'", 'male'),
       ('yusra@lr.com', '123', "Yusra", 'female'),
       ('ea@ea.com', '123', "issam", 'male');

-- insert courses computer , chemistry , physical education
INSERT INTO courses(course_name)
VALUES ('computer'),
       ('chemistry'),
       ('physical education');

-- insert marks for the students
INSERT INTO marks(student_id, course_id, mark)
VALUES (1, 1, 80),
       (1, 2, 90),
       (1, 3, 100),
       (2, 1, 50),
       (2, 2, 60),
       (2, 3, 70),
       (3, 1, 66),
       (3, 2, 66),
       (3, 3, 66);
