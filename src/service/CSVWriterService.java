package service;

import interfaces.WriterService;
import model.Course;
import model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVWriterService implements WriterService {
    private final String fileName;

    public CSVWriterService(String id, String semester) {

        this.fileName = id + "_" + semester;
    }

    public CSVWriterService(String semester) {
        this.fileName = semester;
    }

    /**
     * A method to write the result list of courses to a CSV file
     * @param resultCourseList: the result list of courses needed to be written to CSV file
     * @return boolean indicates whether the list can be written or not
     */
    @Override
    public boolean writeCourses(ArrayList<Course> resultCourseList) {
        PrintWriter output;
        try {
            output = new PrintWriter(new FileWriter("src/files/courses/" + fileName + "_courses.csv"));
            output.println("Course ID,Name,Credit");

            for (Course course : resultCourseList) {
                output.println(course.toString());
            }

            output.close();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * A method to write the result list of students to a CSV file
     * @param resultStudentList: the result list of students needed to be written to CSV file
     * @return boolean indicates whether the list can be written or not
     */
    @Override
    public boolean writeStudents(ArrayList<Student> resultStudentList) {
        PrintWriter output;
        try {
            output = new PrintWriter(new FileWriter("src/files/students/" + fileName + "_students.csv"));
            output.println("Student ID,Name,Birthdate");

            for (Student student : resultStudentList) {
                output.println(student.toString());
            }

            output.close();

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
