package repository;

import model.Enrollment;

import java.util.ArrayList;

public interface StudentEnrollmentManager {
    boolean add(String studentID, String courseID, String semester);
    boolean delete(String studentID, String courseID, String semester);
    Enrollment getOne(String studentID, String courseID, String semester);
    ArrayList<Enrollment> getAll();
}
