package utility;

import model.Course;
import model.Student;

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
     * A method to check if the enrollment is valid
     * @param student: the Student Object of the enrollment
     * @param course: the Course Object of the enrollment
     * @param semester: the semester of the enrollment
     * @return boolean indicates whether the enrollment is valid
     */
    public static boolean checkEnrollment(Student student, Course course, String semester) {
        return student != null && course != null && checkSemester(semester);
    }
}
