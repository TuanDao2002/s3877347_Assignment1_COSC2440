package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentEnrollmentManagerImpl;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllEnrollmentsTest {
    private final StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl();
    private final int expectedSize = 15;
    @BeforeEach
    void setUp() {
        semi.populateData();
    }

    @Test
    void getAllEnrollmentsTest1() {
        // test if the system can get the accurate size of data from csv file
        assertEquals(expectedSize, semi.getAll().size());
    }

    @Test
    void getAllEnrollmentsTest2() {
        // test if the system can reduce the size after deleting an enrollment
        semi.delete("S102192", "PHYS1230", "2021A");
        assertEquals(expectedSize - 1, semi.getAll().size());
    }

    @Test
    void getAllEnrollmentsTest3() {
        // test if the system can increase the size after adding an enrollment
        semi.add("S102732", "PHYS1230", "2021A");
        assertEquals(expectedSize + 1, semi.getAll().size());
    }

    @Test
    void getAllEnrollmentsTest4() {
        // test if the system cannot change the size if the enrollment is not deleted successfully
        semi.delete("S10219", "PHYS1230", "2021A");
        assertEquals(expectedSize, semi.getAll().size());
    }

    @Test
    void getAllEnrollmentsTest5() {
        // test if the system cannot change the size if the enrollment is not added successfully
        semi.add("S102192", "PHYS1230", "2020A");
        assertEquals(expectedSize, semi.getAll().size());
    }
}
