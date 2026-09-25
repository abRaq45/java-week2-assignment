package StudentManagementSystem.model;

import java.util.List;

public class Student {

    private int id;
    private String name;
    private int age;
    private double grade;
    private List<String> subjects;

    public Student(int id, String name, int age, double grade, List<String> subjects) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}