package org.example;

import jdbc.Student;
import jdbc.StudentDAO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();

        while (true) {

            System.out.println("\n===== JDBC Student Management System =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter student grade: ");
                    String grade = scanner.nextLine();

                    Student student = new Student(
                            id,
                            name,
                            age,
                            grade
                    );

                    studentDAO.insertRecord(student);
                    break;


                case 2:
                    studentDAO.getAllRecords();
                    break;


                case 3:
                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new student name: ");
                    String updatedName = scanner.nextLine();

                    System.out.print("Enter new student age: ");
                    int updatedAge = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new student grade: ");
                    String updatedGrade = scanner.nextLine();

                    Student updatedStudent = new Student(
                            updateId,
                            updatedName,
                            updatedAge,
                            updatedGrade
                    );

                    studentDAO.updateRecord(updatedStudent);
                    break;


                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();

                    studentDAO.deleteRecord(deleteId);
                    break;


                case 5:
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;


                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}