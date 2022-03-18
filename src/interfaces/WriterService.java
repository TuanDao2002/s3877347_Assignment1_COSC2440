package interfaces;

import model.Course;
import model.Student;

import java.util.ArrayList;

public interface WriterService {
    boolean writeCoursesOfOneStudentOneSem(ArrayList<Course> resultCourseList);
    boolean writeStudentsOfOneCourseOneSem(ArrayList<Student> resultStudentList);
    boolean writeCoursesOfOneSem(ArrayList<Course> resultCourseList);
}
