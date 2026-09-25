package StudentManagementSystem.main;

import StudentManagementSystem.model.Student;
import StudentManagementSystem.service.StudentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentManager manager = new StudentManager();

        // Load previously saved students
        manager.loadFromFile();


        while (true) {

            System.out.println("\n===== Student Management System =====");

            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Calculate Class Average");
            System.out.println("8. Exit");


            int choice = readInt(
                    scanner,
                    "Enter your choice: "
            );


            switch (choice) {


                // ADD STUDENT
                case 1:

                    addStudent(scanner, manager);

                    break;


                // DISPLAY ALL
                case 2:

                    manager.displayAll();

                    break;


                // SEARCH BY ID
                case 3:

                    searchById(scanner, manager);

                    break;


                // SEARCH BY NAME
                case 4:

                    searchByName(scanner, manager);

                    break;


                // UPDATE
                case 5:

                    updateStudent(scanner, manager);

                    break;


                // DELETE
                case 6:

                    deleteStudent(scanner, manager);

                    break;


                // AVERAGE
                case 7:

                    double average = manager.calculateAverage();

                    if (manager.isEmpty()) {

                        System.out.println(
                                "No students available."
                        );

                    } else {

                        System.out.printf(
                                "Class Average Grade: %.2f%n",
                                average
                        );
                    }

                    break;


                // EXIT
                case 8:

                    manager.saveToFile();

                    System.out.println(
                            "Exiting Student Management System..."
                    );

                    scanner.close();

                    return;


                default:

                    System.out.println(
                            "Invalid choice. Please enter a number between 1 and 8."
                    );
            }
        }
    }


    // ADD STUDENT
    private static void addStudent(
            Scanner scanner,
            StudentManager manager) {

        System.out.println("\n----- Add Student -----");


        int id;

        while (true) {

            id = readPositiveInt(
                    scanner,
                    "Enter student ID: "
            );


            if (manager.searchById(id) != null) {

                System.out.println(
                        "Student with this ID already exists."
                );

            } else {

                break;
            }
        }


        String name = readNonEmptyString(
                scanner,
                "Enter student name: "
        );


        int age = readPositiveInt(
                scanner,
                "Enter student age: "
        );


        double grade = readGrade(
                scanner,
                "Enter student grade (0-100): "
        );


        List<String> subjects = readSubjects(scanner);


        Student student = new Student(
                id,
                name,
                age,
                grade,
                subjects
        );


        manager.addStudent(student);
    }


    // SEARCH BY ID
    private static void searchById(
            Scanner scanner,
            StudentManager manager) {

        System.out.println("\n----- Search Student by ID -----");


        int id = readPositiveInt(
                scanner,
                "Enter student ID: "
        );


        Student student = manager.searchById(id);


        if (student != null) {

            displayStudent(student);

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }


    // SEARCH BY NAME
    private static void searchByName(
            Scanner scanner,
            StudentManager manager) {

        System.out.println("\n----- Search Student by Name -----");


        String name = readNonEmptyString(
                scanner,
                "Enter student name: "
        );


        Student student = manager.searchByName(name);


        if (student != null) {

            displayStudent(student);

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }


    // UPDATE STUDENT
    private static void updateStudent(
            Scanner scanner,
            StudentManager manager) {

        System.out.println("\n----- Update Student -----");


        int id = readPositiveInt(
                scanner,
                "Enter student ID to update: "
        );


        Student existingStudent = manager.searchById(id);


        if (existingStudent == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }


        System.out.println("Current student details:");

        displayStudent(existingStudent);


        double newGrade = readGrade(
                scanner,
                "Enter new grade (0-100): "
        );


        List<String> newSubjects = readSubjects(scanner);


        boolean updated = manager.updateStudent(
                id,
                newGrade,
                newSubjects
        );


        if (updated) {

            System.out.println(
                    "Student updated successfully."
            );

        } else {

            System.out.println(
                    "Student could not be updated."
            );
        }
    }


    // DELETE STUDENT
    private static void deleteStudent(
            Scanner scanner,
            StudentManager manager) {

        System.out.println("\n----- Delete Student -----");


        int id = readPositiveInt(
                scanner,
                "Enter student ID to delete: "
        );


        Student student = manager.searchById(id);


        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }


        System.out.println("Student details:");

        displayStudent(student);


        String confirmation = readNonEmptyString(
                scanner,
                "Are you sure you want to delete this student? (yes/no): "
        );


        if (confirmation.equalsIgnoreCase("yes")) {

            boolean deleted = manager.deleteStudent(id);


            if (deleted) {

                System.out.println(
                        "Student deleted successfully."
                );

            } else {

                System.out.println(
                        "Student could not be deleted."
                );
            }

        } else {

            System.out.println(
                    "Delete operation cancelled."
            );
        }
    }


    // READ INTEGER
    private static int readInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);


            if (scanner.hasNextInt()) {

                int value = scanner.nextInt();

                scanner.nextLine();

                return value;

            } else {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }


    // READ POSITIVE INTEGER
    private static int readPositiveInt(
            Scanner scanner,
            String message) {

        while (true) {

            int value = readInt(
                    scanner,
                    message
            );


            if (value > 0) {

                return value;
            }


            System.out.println(
                    "Value must be greater than 0."
            );
        }
    }


    // READ NON-EMPTY STRING
    private static String readNonEmptyString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);


            String value = scanner.nextLine().trim();


            if (!value.isEmpty()) {

                return value;
            }


            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }


    // READ GRADE
    private static double readGrade(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);


            if (scanner.hasNextDouble()) {

                double grade = scanner.nextDouble();

                scanner.nextLine();


                if (grade >= 0 && grade <= 100) {

                    return grade;
                }


                System.out.println(
                        "Grade must be between 0 and 100."
                );

            } else {

                System.out.println(
                        "Invalid grade. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }


    // READ SUBJECTS
    private static List<String> readSubjects(
            Scanner scanner) {

        while (true) {

            System.out.print(
                    "Enter subjects separated by comma: "
            );


            String input = scanner.nextLine().trim();


            if (input.isEmpty()) {

                System.out.println(
                        "At least one subject is required."
                );

                continue;
            }


            String[] subjectArray = input.split(",");


            List<String> subjects = new ArrayList<>();


            for (String subject : subjectArray) {

                String trimmedSubject = subject.trim();


                if (!trimmedSubject.isEmpty()) {

                    subjects.add(trimmedSubject);
                }
            }


            if (!subjects.isEmpty()) {

                return subjects;
            }


            System.out.println(
                    "Please enter at least one valid subject."
            );
        }
    }


    // DISPLAY ONE STUDENT
    private static void displayStudent(
            Student student) {

        System.out.println("------------------------------");

        System.out.println(
                "ID       : " + student.getId()
        );

        System.out.println(
                "Name     : " + student.getName()
        );

        System.out.println(
                "Age      : " + student.getAge()
        );

        System.out.println(
                "Grade    : " + student.getGrade()
        );

        System.out.println(
                "Subjects : " + student.getSubjects()
        );

        System.out.println(
                "------------------------------"
        );
    }
}