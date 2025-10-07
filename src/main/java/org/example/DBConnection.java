package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Handles database connection setup using environment variables
public class DBConnection {

    // Read database credentials from environment variables
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASS");

    // Get a database connection
    public static Connection getConnection() {
        try {
            if (URL == null || USER == null || PASSWORD == null) {
                System.err.println("Database environment variables (DB_URL, DB_USER, DB_PASS) are not set!");
                return null;
            }
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
