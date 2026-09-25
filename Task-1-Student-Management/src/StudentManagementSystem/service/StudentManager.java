package StudentManagementSystem.service;

import StudentManagementSystem.model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students = new ArrayList<>();

    private final String FILE_PATH = "data/students.txt";


    // CREATE
    public void addStudent(Student student) {

        students.add(student);

        System.out.println("Student added successfully.");
    }


    // READ - Display all students
    public void displayAll() {

        if (students.isEmpty()) {

            System.out.println("No students found.");

            return;
        }

        System.out.println("\n==================== Student Records ====================");

        System.out.printf(
                "%-10s %-20s %-10s %-10s %-30s%n",
                "ID",
                "Name",
                "Age",
                "Grade",
                "Subjects"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Student student : students) {

            System.out.printf(
                    "%-10d %-20s %-10d %-10.2f %-30s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getGrade(),
                    student.getSubjects()
            );
        }

        System.out.println(
                "--------------------------------------------------------------------------"
        );
    }


    // SEARCH BY ID
    public Student searchById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {

                return student;
            }
        }

        return null;
    }


    // SEARCH BY NAME
    public Student searchByName(String name) {

        for (Student student : students) {

            if (student.getName().equalsIgnoreCase(name)) {

                return student;
            }
        }

        return null;
    }


    // UPDATE
    public boolean updateStudent(
            int id,
            double grade,
            List<String> subjects) {

        Student student = searchById(id);

        if (student == null) {

            return false;
        }

        student.setGrade(grade);

        student.setSubjects(subjects);

        return true;
    }


    // DELETE
    public boolean deleteStudent(int id) {

        Student student = searchById(id);

        if (student == null) {

            return false;
        }

        students.remove(student);

        return true;
    }


    // CALCULATE CLASS AVERAGE
    public double calculateAverage() {

        if (students.isEmpty()) {

            return 0;
        }

        double total = 0;

        for (Student student : students) {

            total += student.getGrade();
        }

        return total / students.size();
    }


    // CHECK IF LIST IS EMPTY
    public boolean isEmpty() {

        return students.isEmpty();
    }


    // SAVE DATA TO FILE
    public void saveToFile() {

        try {

            File file = new File(FILE_PATH);

            File parentDirectory = file.getParentFile();

            if (!parentDirectory.exists()) {

                parentDirectory.mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file)
            );

            for (Student student : students) {

                String subjects = String.join(
                        ",",
                        student.getSubjects()
                );

                writer.write(
                        student.getId()
                                + "|"
                                + student.getName()
                                + "|"
                                + student.getAge()
                                + "|"
                                + student.getGrade()
                                + "|"
                                + subjects
                );

                writer.newLine();
            }

            writer.close();

            System.out.println("Student data saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving student data: "
                            + e.getMessage()
            );
        }
    }


    // LOAD DATA FROM FILE
    public void loadFromFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            return;
        }

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader(file)
            );

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {

                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length != 5) {

                    continue;
                }

                int id = Integer.parseInt(data[0]);

                String name = data[1];

                int age = Integer.parseInt(data[2]);

                double grade = Double.parseDouble(data[3]);

                String[] subjectArray = data[4].split(",");

                List<String> subjects = new ArrayList<>();

                for (String subject : subjectArray) {

                    subjects.add(subject.trim());
                }

                Student student = new Student(
                        id,
                        name,
                        age,
                        grade,
                        subjects
                );

                students.add(student);
            }

            reader.close();

            System.out.println("Student data loaded successfully.");

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error while loading student data: "
                            + e.getMessage()
            );
        }
    }
}