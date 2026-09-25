# JDBC Integration

A Java application that connects to MySQL using JDBC and performs CRUD operations on student records.

## Features

- Connect Java application with MySQL
- Create students table
- Insert student records
- Display all student records
- Update student records
- Delete student records
- Use PreparedStatement for SQL operations
- Handle SQL exceptions
- Menu-driven console application

## Technologies Used

- Java
- JDBC
- MySQL
- Maven
- PreparedStatement
- DAO Pattern

## Project Structure

JDBC_Integration
├── src
│   └── main
│       ├── java
│       │   ├── jdbc
│       │   │   ├── DatabaseConnection.java
│       │   │   ├── Student.java
│       │   │   └── StudentDAO.java
│       │   └── org.example
│       │       └── Main.java
│       └── resources
│           └── db.properties
├── schema.sql
├── pom.xml
└── UML_Class_Diagram.png

## CRUD Operations

1. Insert Student
2. Display All Students
3. Update Student
4. Delete Student

## How to Run

1. Create the MySQL database using `schema.sql`.
2. Configure database details in `db.properties`.
3. Open the project in IntelliJ IDEA.
4. Run `Main.java`.
5. Select an operation from the menu.

## Database

Database used:

`jdbc_assignment`

Table:

`students`

Columns:

- id
- name
- age
- grade

## UML Class Diagram

![UML Class Diagram](UML_Class_Diagram.png)