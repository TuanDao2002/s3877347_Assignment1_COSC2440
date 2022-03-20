package utility.input;

import java.util.Scanner;

public final class InputGetter {
    private final static Scanner scanner = new Scanner(System.in);
    private InputGetter(){}

    /**
     * A method to get student ID input
     * @return the student ID
     */
    public static String getStudentID() {
        System.out.println("Enter student ID: ");
        String studentID = scanner.nextLine();
        while (studentID.isEmpty()) {
            System.out.println("Student ID is missing. Enter again: ");
            studentID = scanner.nextLine();
        }

        return studentID;
    }

    /**
     * A method to get course ID input
     * @return the course ID
     */
    public static String getCourseID() {
        System.out.println("Enter course ID: ");
        String courseID = scanner.nextLine();
        while (courseID.isEmpty()) {
            System.out.println("Course ID is missing. Enter again: ");
            courseID = scanner.nextLine();
        }

        return courseID;
    }

    /**
     * A method to get semester input
     * @return the semester ID
     */
    public static String getSemester() {
        System.out.println("Enter semester: ");
        String semester = scanner.nextLine();
        while (semester.isEmpty()) {
            System.out.println("Semester is missing. Enter again: ");
            semester = scanner.nextLine();
        }

        return semester;
    }
}

