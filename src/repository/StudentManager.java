package repository;

import model.Student;

public interface StudentManager {
    Student getStudentById(String studentID);
}
