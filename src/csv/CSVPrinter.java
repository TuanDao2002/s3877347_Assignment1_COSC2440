package csv;

import model.Course;
import model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVPrinter {
    private final String fileName;
    private String directory;

    public CSVPrinter(String id, String semester) {

        this.fileName = id + "_" + semester;
    }

    public CSVPrinter(String semester) {
        this.fileName = semester;
    }

    public String getDirectory() {
        return directory;
    }

    /**
     * A method to write the result list of courses to a CSV file
     * @param resultCourseList: the result list of courses needed to be written to CSV file
     * @return boolean indicates whether the list can be written or not
     */
    public boolean writeCourses(ArrayList<Course> resultCourseList) {
        this.directory = "src/files/courses/" + fileName + "_courses.csv";
        PrintWriter output;
        try {
            output = new PrintWriter(new FileWriter(directory));
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
    public boolean writeStudents(ArrayList<Student> resultStudentList) {
        this.directory = "src/files/students/" + fileName + "_students.csv";
        PrintWriter output;
        try {
            output = new PrintWriter(new FileWriter(directory));
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