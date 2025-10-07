package org.example;

// Entry point of Lab 1
public class Main {
    public static void main(String[] args) {
        UniversityManager manager = new UniversityManager();

        // Create instructors
        Instructor i1 = new Instructor(1, "Dr. Smith");
        Instructor i2 = new Instructor(2, "Prof. Alice");

        // Create courses
        Course c1 = new Course("CS101", "Intro to CS", 3, i1);
        Course c2 = new Course("MATH201", "Advanced Math", 4, i2);

        manager.addCourse(c1);
        manager.addCourse(c2);

        // Create students
        Student s1 = new UndergraduateStudent(1, "John Doe", 85);
        Student s2 = new GraduateStudent(2, "Mary Jane", 90);

        manager.addStudent(s1);
        manager.addStudent(s2);

        // Enroll students
        manager.enrollStudentInCourse(s1, c1);
        manager.enrollStudentInCourse(s2, c2);

        // Display everything
        System.out.println("\nStudents ");
        manager.showAllStudents();

        System.out.println("\n Courses ");
        manager.showAllCourses();
    }
}
