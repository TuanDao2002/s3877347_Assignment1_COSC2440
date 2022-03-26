package test;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentEnrollmentManagerImpl;
import utility.converter.DateConverter;

import static org.junit.jupiter.api.Assertions.*;

public class GetStudentByIdTest {
    private final StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl();
    Student student1 = new Student("S101312", "Alex Mike", DateConverter.stringToDate("10/13/1998"));
    Student student2 = new Student("S102732", "Mark Duong", DateConverter.stringToDate("8/28/2001"));

    @BeforeEach
    void setUp() {
        semi.populateData();
    }

    @Test
    void getStudentByIdTest1() {
        // test if the student extracted by an ID is accurate
        assertEquals(student1, semi.getStudentById("S101312"));
    }

    @Test
    void getStudentByIdTest2() {
        // test if the student extracted by an ID is accurate
        assertEquals(student2, semi.getStudentById("S102732"));
    }

    @Test
    void getStudentByIdTest3() {
        // test if 2 students with different IDs are different
        assertNotEquals(semi.getStudentById("S101312"), semi.getStudentById("S102732"));
    }

    @Test
    void getStudentByIdTest4() {
        // test if a student with an unavailable ID is null
        assertNull(semi.getStudentById("ASD"));
    }
}
