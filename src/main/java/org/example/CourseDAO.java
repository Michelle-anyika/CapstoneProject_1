package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseDAO handles CRUD operations for the "courses" table.
 */
public class CourseDAO {

    // ---------------- CREATE ----------------
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses(code, name, credits) VALUES(?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot add course.");
                return;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getCode());
            stmt.setString(2, course.getName());
            stmt.setInt(3, course.getCredits());
            stmt.executeUpdate();

            System.out.println("Course added: " + course.getName());

        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while adding course.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
    }

    // ---------------- READ ----------------
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot fetch courses.");
                return list;
            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Course(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getInt("credits"),
                        new Instructor(0, "N/A")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while retrieving courses.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
        return list;
    }

    // UPDATE
    public void updateCourse(int id, String newName, int newCredits) {
        String sql = "UPDATE courses SET name = ?, credits = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot update course.");
                return;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newName);
            stmt.setInt(2, newCredits);
            stmt.setInt(3, id);
            stmt.executeUpdate();

            System.out.println("Course updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while updating course.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
    }

    //DELETE
    public void deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection failed. Cannot delete course.");
                return;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Course deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Unexpected null encountered while deleting course.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
    }
}
