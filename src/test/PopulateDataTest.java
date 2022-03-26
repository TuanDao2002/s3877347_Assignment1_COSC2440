package test;

import org.junit.jupiter.api.Test;
import repository.StudentEnrollmentManagerImpl;

import static org.junit.jupiter.api.Assertions.*;

public class PopulateDataTest {
    @Test
    void populateDataTest1() {
        // test if the system can populate the data with default csv file
        StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl();
        assertTrue(semi.populateData());
    }

    @Test
    void populateDataTest2() {
        // test if the system can populate the data with sample csv file from user
        StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl("sample");
        assertTrue(semi.populateData());
    }

    @Test
    void populateDataTest3() {
        // test if the system cannot populate data with csv file that has duplicate
        StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl("test_duplicate");
        assertFalse(semi.populateData());
    }

    @Test
    void populateDataTest4() {
        // test if the system cannot populate data with csv file has missing field
        StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl("test_fields");
        assertFalse(semi.populateData());
    }

    @Test
    void populateDataTest5() {
        // test if the system cannot populate data with csv file has invalid semester format
        StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl("test_semester");
        assertFalse(semi.populateData());
    }

    @Test
    void populateDataTest6() {
        // test if the system cannot populate data with csv file has missing line
        StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl("test_missing_line");
        assertFalse(semi.populateData());
    }

    @Test
    void populateDataTest7() {
        // test if the system cannot populate data with an empty csv file
        StudentEnrollmentManagerImpl semi = new StudentEnrollmentManagerImpl("test_empty_file");
        assertFalse(semi.populateData());
    }
}

