package utility;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.ArrayList;

public interface Display {
    void displayEnrollments(ArrayList<Enrollment> enrollmentList);
    void displayStudents(ArrayList<Student> studentList);
    void displayCourses(ArrayList<Course> courseList);
}
