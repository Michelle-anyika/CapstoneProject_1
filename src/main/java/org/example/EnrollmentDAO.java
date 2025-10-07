package org.example;

import java.sql.*;

public class EnrollmentDAO {

    // Enroll a student in a course
    public void enrollStudent(int studentId, int courseId) {
        String sql = "INSERT INTO enrollments(student_id, course_id) VALUES(?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Cannot connect to database.");
                return;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();

            System.out.println("Student enrolled successfully.");

        } catch (SQLException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        } finally {
            // Close resources safely
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e.getMessage());
            }
        }
    }

    // View all enrollments with student name and course name
    public void viewEnrollments() {
        String sql = "SELECT s.name AS student_name, c.name AS course_name " +
                     "FROM enrollments e " +
                     "JOIN students s ON e.student_id = s.id " +
                     "JOIN courses c ON e.course_id = c.id " +
                     "ORDER BY s.name";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Cannot connect to database.");
                return;
            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("\n--- Enrollments ---");
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                System.out.println(rs.getString("student_name") + " -> " + rs.getString("course_name"));
            }

            if (!hasResults) {
                System.out.println("No enrollments found.");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing enrollments: " + e.getMessage());
        } finally {
            // Close resources safely
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing database resources: " + e.getMessage());
            }
        }
    }
}
