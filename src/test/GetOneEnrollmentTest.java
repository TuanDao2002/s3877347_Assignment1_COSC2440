package test;

import model.Course;
import model.Enrollment;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentEnrollmentManagerImpl;
import utility.converter.DateConverter;

import static org.junit.jupiter.api.Assertions.*;

public class GetOneEnrollmentTest {
    private final StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl();
    Student student1 = new Student("S101312", "Alex Mike", DateConverter.stringToDate("10/13/1998"));
    Student student2 = new Student("S102732", "Mark Duong", DateConverter.stringToDate("8/28/2001"));
    Course course1 = new Course("COSC4030", "Theory of Computation", 5);
    Course course2 = new Course("PHYS1230", "Introductory Human Physiology", 4);

    @BeforeEach
    void setUp() {
        semi.populateData();
    }

    @Test
    void getOneEnrollmentTest1() {
        // test if the system can get an accurate enrollment with student ID, course ID and semester
        Enrollment enrollment = new Enrollment(student1, course1, "2020C");
        assertEquals(enrollment, semi.getOne("S101312", "COSC4030", "2020C"));
    }

    @Test
    void getOneEnrollmentTest2() {
        // test if the system can get an accurate enrollment with student ID, course ID and semester
        Enrollment enrollment = new Enrollment(student1, course2, "2021A");
        assertEquals(enrollment, semi.getOne("S101312", "PHYS1230", "2021A"));
    }

    @Test
    void getOneEnrollmentTest3() {
        // test if the system can get an accurate enrollment with student ID, course ID and semester
        Enrollment enrollment = new Enrollment(student2, course1, "2020C");
        assertEquals(enrollment, semi.getOne("S102732", "COSC4030", "2020C"));
    }
}
