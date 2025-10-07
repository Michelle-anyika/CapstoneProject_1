package org.example;

// Base class for all students
public abstract class Student {
    private int id;
    private String name;

    // Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters (encapsulation)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Abstract method for polymorphism (different GPA formulas)
    public abstract double calculateGPA();

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}
