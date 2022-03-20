package utility.validator;

import repository.StudentEnrollmentManager;
import repository.StudentEnrollmentManagerImpl;

public final class SystemValidator {
    private SystemValidator(){}

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
