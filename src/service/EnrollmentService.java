package service;

import interfaces.StudentEnrollmentManager;
import model.Enrollment;

public class EnrollmentService {
    private StudentEnrollmentManager studentEnrollmentManager;

    public void displayAll() {
        for (Enrollment enrollment : studentEnrollmentManager.getAll()) {
            System.out.println(enrollment);
        }
    }

    public EnrollmentService(StudentEnrollmentManager studentEnrollmentManager) {
        this.studentEnrollmentManager = studentEnrollmentManager;
    }

    public void addEnrollment(String studentID, String courseID, String semester) {
        studentEnrollmentManager.add(studentID, courseID, semester);
    }

    public void deleteEnrollment(String studentID, String courseID, String semester) {
        studentEnrollmentManager.delete(studentID, courseID, semester);
    }
}
