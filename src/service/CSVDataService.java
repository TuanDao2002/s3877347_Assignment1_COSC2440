package service;

import interfaces.DataService;
import model.Course;
import model.Enrollment;
import model.Student;
import utility.DateConverter;
import utility.StringConverter;
import utility.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVDataService implements DataService {
    private static String csvDataFileName = "default.csv";

    public CSVDataService() {}

    public CSVDataService(String fileName) {
        csvDataFileName = fileName;
    }

    /**
     * A method to verify and add new Student object to the ArrayList
     * @param studentArrayList: the list of Student objects
     * @param inReader: the StringTokenizer object used to read the field values in CSV file
     * @return a new Student object from CSV file or null if the field value is invalid
     */
    private Student getNewStudent(ArrayList<Student> studentArrayList, StringTokenizer inReader) {
        // add new Student object to studentArrayList
        String studentID = inReader.nextToken();
        String studentName = inReader.nextToken();

        String birthDateString = inReader.nextToken();
        Date birthDate = DateConverter.stringToDate(birthDateString);

        if (!Validator.checkDate(birthDate)) {
            System.out.println(csvDataFileName + " has invalid date format.");
            return null;
        }

        Student newStudent = new Student(studentID, studentName, birthDate);

        // if there is duplicate, return the new object immediately without adding it to the arraylist and continuing the program
        for (Student s : studentArrayList) {
            if (s.toString().equals(newStudent.toString())) {
                return newStudent;
            }
        }

        studentArrayList.add(newStudent);
        return newStudent;
    }

    /**
     * A method to verify and add new Course object to the ArrayList
     * @param courseArrayList: the list of Student objects
     * @param inReader: the StringTokenizer object used to read the field values in CSV file
     * @return a new Course object from CSV file or null if the field value is invalid
     */
    private Course getNewCourse(ArrayList<Course> courseArrayList, StringTokenizer inReader) {
        // add new Course object to courseArrayList
        String courseID = inReader.nextToken();
        String courseName = inReader.nextToken();

        String creditString = inReader.nextToken();
        int credits = StringConverter.stringToInt(creditString);

        if (!Validator.checkCredit(credits)) {
            System.out.println(csvDataFileName + " has invalid credit format.");
            return null;
        }

        Course newCourse = new Course(courseID, courseName, credits);

        // if there is duplicate, return the new object immediately without adding it to the arraylist and continuing the program
        for (Course c: courseArrayList) {
            if (c.toString().equals(newCourse.toString())) {
                return newCourse;
            }
        }

        courseArrayList.add(newCourse);
        return newCourse;
    }

    /**
     * A method to verify and add new Enrollment object to the ArrayList
     * @param enrollmentArrayList: the list of Enrollment objects
     * @param newStudent: the list of Student objects
     * @param newCourse: the list of Course objects
     * @param inReader: the StringTokenizer object used to read the field values in CSV file
     * @return a new Enrollment object from CSV file or null if the field value is invalid
     */
    private Enrollment getNewEnrollment(ArrayList<Enrollment> enrollmentArrayList, Student newStudent, Course newCourse, StringTokenizer inReader) {
        // add new Enrollment object to enrollmentArrayList
        String semester = inReader.nextToken();
        if (!Validator.checkSemester(semester)) {
            System.out.println(csvDataFileName + " has invalid semester format.");
            return null;
        }

        Enrollment newEnrollment = new Enrollment(newStudent, newCourse, semester);

        // if there is duplicate, return the new object immediately without adding it to the arraylist and continuing the program
        for (Enrollment e : enrollmentArrayList) {
            if (e.toString().equals(newEnrollment.toString())) {
                return newEnrollment;
            }
        }

        enrollmentArrayList.add(newEnrollment);
        return newEnrollment;
    }

    /**
     * A method to read from a CSV file and populate the ArrayList with new data objects
     * @param enrollmentArrayList: the list of Enrollment objects
     * @param studentArrayList: the list of Student objects
     * @param courseArrayList: the list of Course objects
     * @return boolean indicates whether the ArrayLists are populated or not
     */
    @Override
    public boolean populateArrayList(ArrayList<Enrollment> enrollmentArrayList, ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) {
        try {
            Scanner fileInput = new Scanner(new File("src/files/" + csvDataFileName));
            String line;

            while ((line = fileInput.nextLine()) != null) {
                StringTokenizer inReader = new StringTokenizer(line, ",");
                if (inReader.countTokens() != 7) {
                    System.out.println(csvDataFileName + " does not have enough fields");
                    return false;
                } else {
                    Student newStudent = getNewStudent(studentArrayList, inReader);
                    Course newCourse = getNewCourse(courseArrayList, inReader);
                    Enrollment newEnrollment = getNewEnrollment(enrollmentArrayList, newStudent, newCourse, inReader);

                    // empty all arrayLists if there is invalid field in the csv
                    if (newStudent == null || newCourse == null || newEnrollment == null) {
                        enrollmentArrayList.clear();
                        studentArrayList.clear();
                        courseArrayList.clear();
                        return false;
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(csvDataFileName + " is not found.");
            return false;
        } catch (NoSuchElementException e) {
            // reach the end of file
            return true;
        }

        return false;
    }
}
