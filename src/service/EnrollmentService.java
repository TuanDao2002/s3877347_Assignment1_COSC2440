package service;

import repository.StudentEnrollmentManager;
import model.Enrollment;

public class EnrollmentService {
    private StudentEnrollmentManager studentEnrollmentManager;

    public EnrollmentService(StudentEnrollmentManager studentEnrollmentManager) {
        this.studentEnrollmentManager = studentEnrollmentManager;
    }

    public void displayAll() {
        for (Enrollment enrollment : studentEnrollmentManager.getAll()) {
            System.out.println(enrollment);
        }
    }

    public boolean addEnrollment(String studentID, String courseID, String semester) {
        return studentEnrollmentManager.add(studentID, courseID, semester);
    }

    public boolean deleteEnrollment(String studentID, String courseID, String semester) {
        return studentEnrollmentManager.delete(studentID, courseID, semester);
    }
}
