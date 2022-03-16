package repository;

import interfaces.DataService;
import interfaces.StudentEnrollmentManager;
import model.Course;
import model.Enrollment;
import model.Student;
import service.CSVDataService;

import java.util.ArrayList;

public class StudentEnrollmentManagerImpl implements StudentEnrollmentManager {
    private static final ArrayList<Enrollment> studentEnrollmentList = new ArrayList<>();
    private static final  ArrayList<Student> studentList = new ArrayList<>();
    private static final ArrayList<Course> courseList = new ArrayList<>();
    private DataService dataService;

    public StudentEnrollmentManagerImpl(DataService dataService) {
        this.dataService = dataService;
        populateData();
    }

    private void populateData(){
        studentEnrollmentList.clear();
        studentList.clear();
        courseList.clear();
        dataService.populateArrayList(studentEnrollmentList, studentList, courseList);
    }

    @Override
    public boolean add(String studentID, String courseID, String semester) {

        return false;
    }

    @Override
    public boolean update(String studentID, String courseID, String semester, boolean mode) {
        return false;
    }

    @Override
    public boolean delete(String studentID, String courseID, String semester) {
        return false;
    }

    @Override
    public Enrollment getOne(String studentID, String courseID, String semester) {
        return null;
    }

    @Override
    public ArrayList<Enrollment> getAll() {
        return null;
    }
}
