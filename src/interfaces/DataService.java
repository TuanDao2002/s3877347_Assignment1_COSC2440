package interfaces;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.ArrayList;

public interface DataService {
    void clear(ArrayList<Enrollment> enrollmentArrayList, ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList);
    boolean populateArrayList(ArrayList<Enrollment> enrollmentArrayList, ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList);
}
