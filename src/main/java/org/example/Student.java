package org.example;

import java.util.regex.Pattern;

// Base class for all students
public abstract class Student {
    private int id;
    private String name;
    private String email;

    // Email regex pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    // Constructor
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        setEmail(email); // validate email when creating a student
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        // simple email validation
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("Invalid email format: " + email);
            this.email = "null"; // default value if invalid
        } else {
            this.email = email;
        }
    }

    // Abstract method for polymorphism (different GPA formulas)
    public abstract double calculateGPA();

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
