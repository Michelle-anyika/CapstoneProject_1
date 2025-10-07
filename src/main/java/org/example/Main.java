package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        while (true) {
            System.out.println("   UNIVERSITY MANAGEMENT SYSTEM");
            
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enroll Students");
            System.out.println("4. View Reports");
            System.out.println("5. Exit");
            System.out.println("--------------------------------------");
            System.out.print("Enter your choice: ");

            int mainChoice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (mainChoice) {
                case 1 -> studentMenu(sc, studentDAO);
                case 2 -> courseMenu(sc, courseDAO);
                case 3 -> enrollmentMenu(sc, enrollmentDAO);
                case 4 -> viewReports(enrollmentDAO);
                case 5 -> {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    // STUDENT MENU 
    private static void studentMenu(Scanner sc, StudentDAO studentDAO) {
        while (true) {
            System.out.println("\n STUDENT MANAGEMENT ");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Student name: ");
                    String name = sc.nextLine();

                    // Loop until valid email
                    String email;
                    while (true) {
                        System.out.print("Student email: ");
                        email = sc.nextLine();
                        if (EMAIL_PATTERN.matcher(email).matches()) {
                            break;
                        } else {
                            System.out.println("Invalid email format! Please enter a valid email like example@domain.com");
                        }
                    }

                    System.out.print("Is the student graduate? (true/false): ");
                    boolean isGraduate = sc.nextBoolean();
                    System.out.print("Score: ");
                    double score = sc.nextDouble();
                    sc.nextLine(); // consume newline

                    Student s = isGraduate
                            ? new GraduateStudent(0, name, email, score)
                            : new UndergraduateStudent(0, name, email, score);
                    studentDAO.addStudent(s);
                }

                case 2 -> {
                    List<Student> students = studentDAO.getAllStudents();
                    System.out.println("\n--- All Students ---");
                    students.forEach(System.out::println);
                }

                case 3 -> {
                    System.out.print("Student ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New name: ");
                    String newName = sc.nextLine();

                    // Loop until valid new email
                    String newEmail;
                    while (true) {
                        System.out.print("New email: ");
                        newEmail = sc.nextLine();
                        if (EMAIL_PATTERN.matcher(newEmail).matches()) {
                            break;
                        } else {
                            System.out.println("Invalid email format! Please enter a valid email like example@domain.com");
                        }
                    }

                    System.out.print("Is the student graduate? (true/false): ");
                    boolean isGraduate = sc.nextBoolean();
                    System.out.print("New score: ");
                    double newScore = sc.nextDouble();
                    sc.nextLine(); // consume newline

                    studentDAO.updateStudent(id, newName, newEmail, newScore, isGraduate);
                }

                case 4 -> {
                    System.out.print("Student ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    studentDAO.deleteStudent(id);
                }

                case 5 -> { return; }

                default -> System.out.println("Invalid option!");
            }
        }
    }

    //  COURSE MENU 
    private static void courseMenu(Scanner sc, CourseDAO courseDAO) {
        while (true) {
            System.out.println("\n COURSE MANAGEMENT ");
            System.out.println("1. Add Course");
            System.out.println("2. View Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Course code: ");
                    String code = sc.nextLine();
                    System.out.print("Course name: ");
                    String name = sc.nextLine();
                    System.out.print("Credits: ");
                    int credits = sc.nextInt();
                    Course c = new Course(code, name, credits, new Instructor(0, "N/A"));
                    courseDAO.addCourse(c);
                }
                case 2 -> {
                    List<Course> courses = courseDAO.getAllCourses();
                    System.out.println("\n All Courses ");
                    courses.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Course ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New credits: ");
                    int newCredits = sc.nextInt();
                    courseDAO.updateCourse(id, newName, newCredits);
                }
                case 4 -> {
                    System.out.print("Course ID to delete: ");
                    int id = sc.nextInt();
                    courseDAO.deleteCourse(id);
                }
                case 5 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    //  ENROLLMENT MENU 
    private static void enrollmentMenu(Scanner sc, EnrollmentDAO enrollmentDAO) {
        while (true) {
            System.out.println("\n ENROLLMENT MANAGEMENT ");
            System.out.println("1. Enroll a Student");
            System.out.println("2. View Enrollments");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Course ID: ");
                    int cid = sc.nextInt();
                    enrollmentDAO.enrollStudent(sid, cid);
                }
                case 2 -> enrollmentDAO.viewEnrollments();
                case 3 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    //  REPORTS 
    private static void viewReports(EnrollmentDAO enrollmentDAO) {
        System.out.println("\n UNIVERSITY REPORTS ");
        enrollmentDAO.viewEnrollments();
        System.out.println("-------------------------------");
    }
}
