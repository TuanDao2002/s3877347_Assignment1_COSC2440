package test;

import model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentEnrollmentManagerImpl;

import static org.junit.jupiter.api.Assertions.*;

public class GetCourseByIdTest {
    private final StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl();
    Course course1 = new Course("COSC4030", "Theory of Computation", 5);
    Course course2 = new Course("PHYS1230", "Introductory Human Physiology", 4);

    @BeforeEach
    void setUp() {
        semi.populateData();
    }

    @Test
    void getCourseByIdTest1() {
        // test if a course extracted by an ID is accurate
        assertEquals(course1, semi.getCourseById("COSC4030"));
    }

    @Test
    void getCourseByIdTest2() {
        // test if a course extracted by an ID is accurate
        assertEquals(course2, semi.getCourseById("PHYS1230"));
    }

    @Test
    void getCourseByIdTest3() {
        // test if 2 courses with different IDs are different
        assertNotEquals(semi.getCourseById("COSC4030"), semi.getCourseById("PHYS1230"));
    }

    @Test
    void getCourseByIdTest4() {
        // test if a course with an unavailable ID is null
        assertNull(semi.getCourseById("COSC403"));
    }
}
