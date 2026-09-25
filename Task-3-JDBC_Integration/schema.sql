CREATE DATABASE IF NOT EXISTS jdbc_assignment;

USE jdbc_assignment;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    grade VARCHAR(10) NOT NULL
);