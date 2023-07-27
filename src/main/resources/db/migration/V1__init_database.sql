CREATE DATABASE IF NOT EXISTS `uni-db`;
USE `uni-db`;

CREATE TABLE IF NOT EXISTS `subjects`
(
    id   BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `grades`
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    subject_id BIGINT REFERENCES `subjects` (`id`),
    student_id BIGINT REFERENCES `students` (`id`),
    grade      BIGINT
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `students`
(
    id      BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name    VARCHAR(255),
    surname VARCHAR(255),
    age     BIGINT
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `teachers`
(
    id      BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name    VARCHAR(255),
    surname VARCHAR(255),
    subject BIGINT REFERENCES `subjects` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `students_grades`
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    student_id BIGINT REFERENCES `students` (`id`),
    grade_id   BIGINT REFERENCES `grades` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `teachers_students`
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    teacher_id BIGINT REFERENCES `teachers` (`id`),
    student_id BIGINT REFERENCES `students` (`id`)
) ENGINE = InnoDB;

INSERT INTO `subjects`
VALUES (1, 'Math');
INSERT INTO `subjects`
VALUES (2, 'English');
INSERT INTO `subjects`
VALUES (3, 'Physics');
INSERT INTO `subjects`
VALUES (4, 'Chemistry');
INSERT INTO `subjects`
VALUES (5, 'History');
