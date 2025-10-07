package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO handles all CRUD operations for the "students" table.
 */
public class StudentDAO {
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name, email, gpa) VALUES(?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot add student.");
                return;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setDouble(3, student.calculateGPA());
            stmt.executeUpdate();

            System.out.println("Student added: " + student.getName());

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while adding student.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
    }

    // READ
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot fetch students.");
                return students;
            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                double gpa = rs.getDouble("gpa");

                //  back to Undergraduate for simplicity
                students.add(new UndergraduateStudent(id, name, email, gpa * 25));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while retrieving students.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
        return students;
    }

    // UPDATE
    public void updateStudent(int id, String newName, String newEmail, double newScore, boolean isGraduate) {
        String sql = "UPDATE students SET name = ?, email = ?, gpa = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot update student.");
                return;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newName);
            stmt.setString(2, newEmail);

            // GPA formula based on student type
            double newGPA = isGraduate ? (newScore / 100) * 5.0 : (newScore / 100) * 4.0;
            stmt.setDouble(3, newGPA);
            stmt.setInt(4, id);

            stmt.executeUpdate();
            System.out.println("Student updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while updating student.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
    }

    // ---------------- DELETE ----------------
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot delete student.");
                return;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println(" Student deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while deleting student.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
    }
}
