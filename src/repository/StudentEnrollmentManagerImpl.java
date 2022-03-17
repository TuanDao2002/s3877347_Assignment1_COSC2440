package repository;

import interfaces.DataService;
import interfaces.StudentEnrollmentManager;
import model.Course;
import model.Enrollment;
import model.Student;
import service.CSVDataService;
import utility.Validator;

import java.util.ArrayList;

public class StudentEnrollmentManagerImpl implements StudentEnrollmentManager {
    private static final ArrayList<Enrollment> enrollmentList = new ArrayList<>();
    private static final  ArrayList<Student> studentList = new ArrayList<>();
    private static final ArrayList<Course> courseList = new ArrayList<>();
    private DataService dataService;

    public StudentEnrollmentManagerImpl() {
        this.dataService = new CSVDataService();
    }

    public StudentEnrollmentManagerImpl(String fileName) {
        this.dataService = new CSVDataService(fileName);
    }

    public boolean populateData(){
        enrollmentList.clear();
        studentList.clear();
        courseList.clear();
        return dataService.populateArrayList(enrollmentList, studentList, courseList);
    }

    public static ArrayList<Enrollment> getStudentEnrollmentList() {
        return enrollmentList;
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static ArrayList<Student> getStudentList() {
        return studentList;
    }

    public Student getStudentById(String studentID) {
        for (Student s : studentList) {
            if (s.getID().equals(studentID)) {
                return s;
            }
        }

        return null;
    }

    public Course getCourseById(String courseID) {
        for (Course c : courseList) {
            if (c.getID().equals(courseID)) {
                return c;
            }
        }

        return null;
    }

    public boolean containSemester(String semester) {
        for (Enrollment e : enrollmentList) {
            if (e.getSemester().equals(semester)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(String studentID, String courseID, String semester) {
        if (!Validator.checkSemester(semester)) return false;

        Student student = getStudentById(studentID);
        Course course = getCourseById(courseID);

        if (student == null) {
            System.out.println("Student with ID: " + studentID + " does not exist.");
            return false;
        }

        if (course == null) {
            System.out.println("Course with ID: " + courseID + " does not exist.");
            return false;
        }

        enrollmentList.add(new Enrollment(student, course, semester));
        System.out.println("The new enrollment is added.");
        return true;
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
