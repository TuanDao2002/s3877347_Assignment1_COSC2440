package csv;

import model.Course;
import model.Enrollment;
import model.Student;
import repository.DataCollector;
import utility.validator.FieldValidator;
import utility.converter.DateConverter;
import utility.converter.StringConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVDataCollector implements DataCollector {
    private final String csvDataFileName;

    public CSVDataCollector() {
        this.csvDataFileName = "default.csv";
    }

    public CSVDataCollector(String fileName) {
        this.csvDataFileName = fileName + ".csv";
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

        if (!FieldValidator.checkDate(birthDate)) {
            System.out.print(csvDataFileName + " has invalid date format ");
            return null;
        }

        Student newStudent = new Student(studentID, studentName, birthDate);

        // if there is duplicate, return the new object immediately without adding it to the arraylist and continuing the program
        if (studentArrayList.contains(newStudent)) {
            return newStudent;
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

        if (!FieldValidator.checkCredit(credits)) {
            System.out.print(csvDataFileName + " has invalid credit format ");
            return null;
        }

        Course newCourse = new Course(courseID, courseName, credits);

        // if there is duplicate, return the new object immediately without adding it to the arraylist and continuing the program
        if (courseArrayList.contains(newCourse)) {
            return newCourse;
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
        if (!FieldValidator.checkSemester(semester)) {
            System.out.print(csvDataFileName + " has invalid semester format (the semester must be at 21st century with A,B or C letter) ");
            return null;
        }

        Enrollment newEnrollment = new Enrollment(newStudent, newCourse, semester);

        // if there is duplicate, return the new object immediately without adding it to the arraylist and continuing the program
        if (enrollmentArrayList.contains(newEnrollment)) {
            System.out.print("Duplicate enrollment ");
            return null;
        }

        enrollmentArrayList.add(newEnrollment);
        return newEnrollment;
    }

    /**
     * A method to clear all ArrayList
     * @param enrollmentArrayList: the list of Enrollment objects
     * @param studentArrayList: the list of Student objects
     * @param courseArrayList: the list of Course objects
     */
    @Override
    public void clear(ArrayList<Enrollment> enrollmentArrayList, ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) {
        enrollmentArrayList.clear();
        studentArrayList.clear();
        courseArrayList.clear();
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
            Scanner fileInput = new Scanner(new File("files/data/" + csvDataFileName));

            if (!fileInput.hasNextLine()) {
                System.out.println(csvDataFileName + " is empty!");
                return false;
            }

            String line;
            int linePos = 1;

            while ((line = fileInput.nextLine()) != null) {
                if (line.isEmpty()) {
                    System.out.println(csvDataFileName + " has line " + linePos + " empty!");
                    clear(enrollmentArrayList, studentArrayList, courseArrayList);
                    return false;
                }

                StringTokenizer inReader = new StringTokenizer(line, ",");
                if (inReader.countTokens() != 7) {
                    System.out.println(csvDataFileName + " does not have enough fields at line " + linePos + ".");
                    clear(enrollmentArrayList, studentArrayList, courseArrayList);
                    return false;
                } else {
                    Student newStudent = getNewStudent(studentArrayList, inReader);
                    if (newStudent == null) {
                        System.out.println("at line " + linePos + ".");
                        clear(enrollmentArrayList, studentArrayList, courseArrayList);
                        return false;
                    }

                    Course newCourse = getNewCourse(courseArrayList, inReader);
                    if (newCourse == null) {
                        System.out.println("at line " + linePos + ".");
                        clear(enrollmentArrayList, studentArrayList, courseArrayList);
                        return false;
                    }

                    Enrollment newEnrollment = getNewEnrollment(enrollmentArrayList, newStudent, newCourse, inReader);
                    if (newEnrollment == null) {
                        System.out.println("at line " + linePos + ".");
                        clear(enrollmentArrayList, studentArrayList, courseArrayList);
                        return false;
                    }
                }

                linePos++;
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
