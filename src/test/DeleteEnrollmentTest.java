package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentEnrollmentManagerImpl;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteEnrollmentTest {
    private final StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl();

    @BeforeEach
    void setUp() {
        semi.populateData();
    }

    @Test
    void deleteEnrollmentTest1() {
        // test if the system can delete an enrollment
        int beforeSize = semi.getAll().size();
        semi.delete("S101312", "COSC4030", "2020C");
        int afterSize = semi.getAll().size();

        assertEquals(afterSize, beforeSize - 1);
    }

    @Test
    void deleteEnrollmentTest2() {
        // test if the system cannot delete an enrollment if it is invalid
        int beforeSize = semi.getAll().size();
        semi.delete("S101310", "COSC4030", "2021A");
        int afterSize = semi.getAll().size();

        assertEquals(afterSize, beforeSize);
    }

    @Test
    void deleteEnrollmentTest3() {
        // test if the system cannot delete an enrollment if the student ID is not available
        assertFalse(semi.delete("S101319", "COSC4030", "2021A"));
    }

    @Test
    void deleteEnrollmentTest4() {
        // test if the system cannot delete an enrollment if the course ID is not available
        assertFalse(semi.delete("S101312", "COSC403", "2021A"));
    }

    @Test
    void deleteEnrollmentTest5() {
        // test if the system cannot delete an enrollment if the semester is not valid
        assertFalse(semi.delete("S101312", "COSC4030", "2021"));
    }

    @Test
    void deleteEnrollmentTest6() {
        // test if the system cannot delete an enrollment if the enrollment does not exists
        assertFalse(semi.delete("S101312", "COSC4030", "2020A"));
    }
}
