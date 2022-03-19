package utility;

import repository.CourseManager;
import repository.StudentManager;

import java.util.Date;

public final class Validator {
    private Validator(){}

    /**
     * A method to validate a Date object
     * @param date: a Date object required to be validated
     * @return true if the Date object is not null
     */
    public static boolean checkDate(Date date) {
        return date != null;
    }

    /**
     * A method to validate a credit
     * @param num: a credit required to be validated
     * @return true if the credit is greater than zero
     */
    public static boolean checkCredit(int num) {
        return num > 0;
    }

    /**
     * A method to validate semester
     * @param semester: a semester required to be validated
     * @return true if the semester matches with regex format
     */
    public static boolean checkSemester(String semester) {
        return semester.matches("^(19|20)\\d{2}[A-C]$");
    }

    /**
     * A method to check if a student ID exists
     * @param studentManager: the system object that manage Student objects
     * @param studentID: the student ID required to be checked
     * @return boolean indicates whether the student ID exists or not
     */
    public static boolean checkStudent(StudentManager studentManager, String studentID) {
        return studentManager.getStudentById(studentID) != null;
    }

    /**
     * A method to check if a course ID exists
     * @param courseManager: the system object that manage Ctudent objects
     * @param courseID: the course ID required to be checked
     * @return boolean indicates whether the course ID exists or not
     */
    public static boolean checkCourse(CourseManager courseManager, String courseID) {
        return courseManager.getCourseById(courseID) != null;
    }
}
