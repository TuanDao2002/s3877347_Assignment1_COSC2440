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

    /**
     * A method to populate the ArrayLists
     * @return true if the ArrayLists are populated successfully
     */
    public boolean populateData(){
        enrollmentList.clear();
        studentList.clear();
        courseList.clear();
        return dataService.populateArrayList(enrollmentList, studentList, courseList);
    }

    /**
     * A method to get Student object by student ID
     * @param studentID: the student ID of the Student object
     * @return the Student object or null if it does not exist
     */
    public Student getStudentById(String studentID) {
        for (Student s : studentList) {
            if (s.getID().equals(studentID)) {
                return s;
            }
        }

        return null;
    }

    /**
     * A method to get Course object by course ID
     * @param courseID: the course ID of the Course object
     * @return the Course object or null if it does not exist
     */
    public Course getCourseById(String courseID) {
        for (Course c : courseList) {
            if (c.getID().equals(courseID)) {
                return c;
            }
        }

        return null;
    }

    /**
     * A method to add new Enrollment object to ArrayList
     * @param studentID: the student ID of the enrollment
     * @param courseID: the course ID of the enrollment
     * @param semester: the semester of the enrollment
     * @return boolean indicates whether the enrollment is added or not
     */
    @Override
    public boolean add(String studentID, String courseID, String semester) {
        Student student = getStudentById(studentID);
        Course course = getCourseById(courseID);

        if (!Validator.checkEnrollment(student, course, semester)) {
            System.out.println("The enrollment cannot be added.");
            return false;
        }

        if (getOne(studentID, courseID, semester) != null) {
            System.out.println("The enrollment already exists.");
            return false;
        }

        enrollmentList.add(new Enrollment(student, course, semester));
        System.out.println("The new enrollment is added.");
        return true;
    }

    /**
     * A method to update enrollment
     * @param studentID: the student ID of the enrollment
     * @param courseID: the course ID of the enrollment
     * @param semester: the semester of the enrollment
     * @param deleteMode set to true if users want to delete the enrollment. Otherwise, the enrollment will be added
     * @return boolean indicates whether the enrollment is updated or not
     */
    @Override
    public boolean update(String studentID, String courseID, String semester, boolean deleteMode) {
        if (deleteMode) {
            delete(studentID, courseID, semester);
        } else {
            add(studentID, courseID, semester);
        }
        return false;
    }

    /**
     * A method to delete enrollment
     * @param studentID: the student ID of the enrollment
     * @param courseID: the course ID of the enrollment
     * @param semester: the semester of the enrollment
     * @return boolean indicates whether the enrollment is deleted or not
     */
    @Override
    public boolean delete(String studentID, String courseID, String semester) {
        Enrollment deleteEnrollment = getOne(studentID, courseID, semester);
        if (deleteEnrollment == null) return false;
        enrollmentList.remove(deleteEnrollment);
        System.out.println("The enrollment is deleted.");
        return true;
    }

    /**
     * A method to get a specific enrollment
     * @param studentID: the student ID of the enrollment
     * @param courseID: the course ID of the enrollment
     * @param semester: the semester of the enrollment
     * @return the existing enrollment or null if it does not exist
     */
    @Override
    public Enrollment getOne(String studentID, String courseID, String semester) {
        for (Enrollment e : enrollmentList) {
            if (e.getStudent().getID().equals(studentID)
                    && e.getCourse().getID().equals(courseID)
                    && e.getSemester().equals(semester)) {
                return e;
            }
        }
        return null;
    }

    /**
     * A method to return all the enrollments
     * @return list of all the enrollments
     */
    @Override
    public ArrayList<Enrollment> getAll() {
        return enrollmentList;
    }
}
