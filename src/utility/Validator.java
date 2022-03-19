package utility;

import repository.StudentEnrollmentManager;
import repository.StudentEnrollmentManagerImpl;

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
        return semester.matches("^(20)\\d{2}[A-C]$");
    }

    /**
     * A method to check if a student ID exists
     * @param studentEnrollmentManager: the StudentEnrollmentManager object
     * @param studentID: the student ID required to be checked
     * @return boolean indicates whether the student ID exists or not
     */
    public static boolean checkStudent(StudentEnrollmentManager studentEnrollmentManager, String studentID) {
       return studentEnrollmentManager.getStudentById(studentID) != null;
    }

    /**
     * A method to check if a course ID exists
     * @param studentEnrollmentManager: the StudentEnrollmentManager object
     * @param courseID: the course ID required to be checked
     * @return boolean indicates whether the course ID exists or not
     */
    public static boolean checkCourse(StudentEnrollmentManager studentEnrollmentManager, String courseID) {
       return studentEnrollmentManager.getCourseById(courseID) != null;
    }

    /**
     * A method to check if the system object can populate data
     * @param semi: the system object required to check
     * @return boolean indicates whether the data is populated or not
     */
    public static boolean checkData(StudentEnrollmentManagerImpl semi) {
        if (semi.populateData()) {
            System.out.println("Data is populated successfully!");
            return true;
        } else {
            System.out.println("Cannot populate data!");
            return false;
        }
    }
}
