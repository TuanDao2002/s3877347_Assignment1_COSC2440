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
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CSVDataService implements DataService {
    private static String csvDataFileName = "default.csv";

    public CSVDataService() {}

    public CSVDataService(String fileName) {
        csvDataFileName = fileName;
    }

    @Override
    public boolean populateArrayList(ArrayList<Enrollment> enrollmentArrayList, ArrayList<Student> studentArrayList, ArrayList<Course> courseArrayList) {
        try {
            Scanner fileInput = new Scanner(new File("./files/" + csvDataFileName));
            String line;
            while ((line = fileInput.nextLine()) != null) {
                StringTokenizer inReader = new StringTokenizer(line, ",");
                if (inReader.countTokens() != 7) {
                    System.out.println("The CSV file does not have enough fields");
                    return false;
                } else {
                    // add new Student object to studentArrayList
                    String studentID = inReader.nextToken();
                    String studentName = inReader.nextToken();

                    String birthDateString = inReader.nextToken();
                    Date birthDate = DateConverter.stringToDate(birthDateString);

                    if (!Validator.checkDate(birthDate)) {
                        System.out.println(csvDataFileName + " has invalid date format.");
                        return false;
                    }

                    Student newStudent = new Student(studentID, studentName, birthDate);
                    studentArrayList.add(newStudent);

                    // add new Course object to courseArrayList
                    String courseID = inReader.nextToken();
                    String courseName = inReader.nextToken();

                    String creditString = inReader.nextToken();
                    int credits = StringConverter.stringToInt(creditString);

                    if (!Validator.checkInteger(credits)) {
                        System.out.println(csvDataFileName + " has invalid credit format.");
                        return false;
                    }

                    Course newCourse = new Course(courseID, courseName, credits);
                    courseArrayList.add(newCourse);

                    String semester = inReader.nextToken();
                    if (!Validator.checkSemester(semester)) {
                        System.out.println(csvDataFileName + " has invalid semester format.");
                        return false;
                    }

                    enrollmentArrayList.add(new Enrollment(newStudent, newCourse, semester));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(csvDataFileName + " not found.");
            return false;
        }

        return true;
    }
}
