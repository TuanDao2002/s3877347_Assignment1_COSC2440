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
    private final DataService dataService;

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
        for (Student student : studentList) {
            if (student.getID().equals(studentID)) {
                return student;
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
        for (Course course : courseList) {
            if (course.getID().equals(courseID)) {
                return course;
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
        if (student == null) {
            System.out.println("Student with ID: " + studentID + " does not exist.");
            return false;
        }

        Course course = getCourseById(courseID);
        if (course == null) {
            System.out.println("Course with ID: " + courseID + " does not exist.");
            return false;
        }

        if (!Validator.checkSemester(semester)) {
            System.out.println(semester + " is invalid semester format.");
            return false;
        }

        if (getOne(studentID, courseID, semester) != null) {
            System.out.println("The enrollment already exists.");
            return false;
        }

        enrollmentList.add(new Enrollment(student, course, semester));
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
        if (deleteEnrollment == null) {
            System.out.println("The enrollment does not exist");
            return false;
        }
        enrollmentList.remove(deleteEnrollment);
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
        for (Enrollment enrollment : enrollmentList) {
            if (enrollment.getStudent().getID().equals(studentID)
                    && enrollment.getCourse().getID().equals(courseID)
                    && enrollment.getSemester().equals(semester)) {
                return enrollment;
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
