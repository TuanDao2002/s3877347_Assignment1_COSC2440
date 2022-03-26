package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentEnrollmentManagerImpl;

import static org.junit.jupiter.api.Assertions.*;

public class AddEnrollmentTest {
    private final StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl();

    @BeforeEach
    void setUp() {
        semi.populateData();
    }

    @Test
    void addEnrollmentTest1() {
        // test if the system can add a new enrollment
        int beforeSize = semi.getAll().size();
        semi.add("S101312", "COSC4030", "2021A");
        int afterSize = semi.getAll().size();

        assertEquals(afterSize, beforeSize + 1);
    }

    @Test
    void addEnrollmentTest2() {
        // test if the system cannot add a new enrollment if it is invalid
        int beforeSize = semi.getAll().size();
        semi.add("S101312", "COSC403", "2021A");
        int afterSize = semi.getAll().size();

        assertEquals(afterSize, beforeSize);
    }

    @Test
    void addEnrollmentTest3() {
        // test if the system cannot add a new enrollment if the student ID is not available
        assertFalse(semi.add("S101319", "COSC4030", "2021A"));
    }

    @Test
    void addEnrollmentTest4() {
        // test if the system cannot add a new enrollment if the course ID is not available
        assertFalse(semi.add("S101312", "COSC4031", "2021A"));
    }

    @Test
    void addEnrollmentTest5() {
        // test if the system cannot add a new enrollment if the semester is not valid
        assertFalse(semi.add("S101312", "COSC4030", "202A"));
    }

    @Test
    void addEnrollmentTest6() {
        // test if the system cannot add a new enrollment if the enrollment already exists
        assertFalse(semi.add("S101312", "COSC4030", "2020C"));
    }
}
