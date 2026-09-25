package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // INSERT
    public void insertRecord(Student student) {

        String sql = """
                INSERT INTO students (id, name, age, grade)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGrade());

            preparedStatement.executeUpdate();

            System.out.println("Student inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to insert student.");
            System.out.println(e.getMessage());
        }
    }


    // SELECT
    public void getAllRecords() {

        String sql = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\n----- Student Records -----");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String grade = resultSet.getString("grade");

                System.out.println(
                        "ID: " + id +
                                ", Name: " + name +
                                ", Age: " + age +
                                ", Grade: " + grade
                );
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch student records.");
            System.out.println(e.getMessage());
        }
    }


    // UPDATE
    public void updateRecord(Student student) {

        String sql = """
                UPDATE students
                SET name = ?, age = ?, grade = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getGrade());
            preparedStatement.setInt(4, student.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student with ID "
                        + student.getId() + " not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update student.");
            System.out.println(e.getMessage());
        }
    }


    // DELETE
    public void deleteRecord(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student with ID "
                        + id + " not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete student.");
            System.out.println(e.getMessage());
        }
    }
}