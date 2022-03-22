package repository;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.ArrayList;

public interface StudentEnrollmentManager {
    boolean add(String studentID, String courseID, String semester);
    boolean delete(String studentID, String courseID, String semester);
    Enrollment getOne(String studentID, String courseID, String semester);
    ArrayList<Enrollment> getAll();
}
