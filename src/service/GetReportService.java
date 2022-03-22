package service;

import model.Course;
import model.Enrollment;
import model.Student;
import repository.StudentEnrollmentManagerImpl;
import utility.validator.FieldValidator;
import utility.validator.SystemValidator;

import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;


public class GetReportService {
    private StudentEnrollmentManagerImpl studentEnrollmentManagerImpl;

    public GetReportService(StudentEnrollmentManagerImpl studentEnrollmentManagerImpl) {
        this.studentEnrollmentManagerImpl = studentEnrollmentManagerImpl;
    }

    /**
     * A method to return all courses of one student in one semester
     * @param studentID: student ID of the search
     * @param semester: semester of the search
     * @return list of courses
     */
    public ArrayList<Course> getAllCoursesOfOneStudentOneSem(String studentID, String semester) {
        if (!SystemValidator.checkStudent(studentEnrollmentManagerImpl, studentID)) {
            System.out.println("Student with ID: " + studentID + " does not exist.");
            return null;
        }

        if (!FieldValidator.checkSemester(semester)) {
            System.out.println(semester + " has invalid semester format (the semester must be at 21st century with A,B or C letter).");
            return null;
        }

        ArrayList<Course> resultCourseList = new ArrayList<>();
        for (Enrollment enrollment : studentEnrollmentManagerImpl.getAll()) {
            if (enrollment.getStudent().getID().equals(studentID) && enrollment.getSemester().equals(semester)) {
                resultCourseList.add(enrollment.getCourse());
            }
        }

        // use set to prevent duplicates
        Set<Course> set = new LinkedHashSet<>(resultCourseList);
        resultCourseList.clear();
        resultCourseList.addAll(set);

        return resultCourseList;
    }

    /**
     * A method to return all students of one course in one semester
     * @param courseID: course ID of the search
     * @param semester: semester of the search
     * @return list of students
     */
    public ArrayList<Student> getAllStudentsOfOneCourseOneSem(String courseID, String semester) {
        if (!SystemValidator.checkCourse(studentEnrollmentManagerImpl, courseID)) {
            System.out.println("Course with ID: " + courseID + " does not exist.");
            return null;
        }

        if (!FieldValidator.checkSemester(semester)) {
            System.out.println(semester + " has invalid semester format (the semester must be at 21st century with A,B or C letter).");
            return null;
        }

        ArrayList<Student> resultStudentList = new ArrayList<>();
        for (Enrollment enrollment : studentEnrollmentManagerImpl.getAll()) {
            if (enrollment.getCourse().getID().equals(courseID) && enrollment.getSemester().equals(semester)) {
                resultStudentList.add(enrollment.getStudent());
            }
        }

        // use set to prevent duplicates
        Set<Student> set = new LinkedHashSet<>(resultStudentList);
        resultStudentList.clear();
        resultStudentList.addAll(set);

        return resultStudentList;
    }

    /**
     * A method to return all courses offered in one semester
     * @param semester: semester of the search
     * @return list of courses
     */
    public ArrayList<Course> getAllCoursesOfOneSemester(String semester) {
        if (!FieldValidator.checkSemester(semester)) {
            System.out.println(semester + " has invalid semester format (the semester must be at 21st century with A,B or C letter).");
            return null;
        }

        ArrayList<Course> resultCourseList = new ArrayList<>();
        for (Enrollment enrollment : studentEnrollmentManagerImpl.getAll()) {
            if (enrollment.getSemester().equals(semester)) {
                resultCourseList.add(enrollment.getCourse());
            }
        }

        // use set to prevent duplicates
        Set<Course> set = new LinkedHashSet<>(resultCourseList);
        resultCourseList.clear();
        resultCourseList.addAll(set);

        return resultCourseList;

    }
}
