package org.example;

public class Course {
    private String code;
    private String name;
    private int credits;
    private Instructor instructor;

    public Course(String code, String name, int credits, Instructor instructor) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.instructor = instructor;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }

    @Override
    public String toString() {
        return name + " (" + code + ") - " + credits + " credits, Instructor: " + instructor.getName();
    }
}
