package service;

import repository.StudentEnrollmentManager;
import utility.display.Display;
import utility.display.TableDisplay;

public class EnrollmentService {
    private StudentEnrollmentManager studentEnrollmentManager;

    public EnrollmentService(StudentEnrollmentManager studentEnrollmentManager) {
        this.studentEnrollmentManager = studentEnrollmentManager;
    }

    public void displayAll() {
        Display tableDisplay = new TableDisplay();
        tableDisplay.displayEnrollments(studentEnrollmentManager.getAll());
    }

    public boolean addEnrollment(String studentID, String courseID, String semester) {
        return studentEnrollmentManager.add(studentID, courseID, semester);
    }

    public boolean deleteEnrollment(String studentID, String courseID, String semester) {
        return studentEnrollmentManager.delete(studentID, courseID, semester);
    }
}
