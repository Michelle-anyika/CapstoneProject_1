package org.example;

import java.util.*;

// Class that manages collections of students and courses
public class UniversityManager {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Add a new course
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Enroll a student (simplified for Lab 1)
    public void enrollStudentInCourse(Student student, Course course) {
        System.out.println(student.getName() + " enrolled in " + course.getName());
    }

    // Display all students
    public void showAllStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Display all courses
    public void showAllCourses() {
        for (Course c : courses) {
            System.out.println(c);
        }
    }
}
