package interfaces;

import model.Course;
import model.Student;

import java.util.ArrayList;

public interface WriterService {
    boolean writeCourses(ArrayList<Course> resultCourseList);
    boolean writeStudents(ArrayList<Student> resultStudentList);
}
