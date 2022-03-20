package utility.display;

import model.Course;
import model.Enrollment;
import model.Student;
import repository.StudentEnrollmentManagerImpl;

import java.util.ArrayList;

public class TableDisplay implements Display{
    public TableDisplay(){}

    private int[] getColumnWidthsOfStudents(ArrayList<Student> students) {
        String[] labels = new String[]{"Student ID", "Name", "Birthdate"};

        int numOfAttributes = students.get(0).toString().split(",").length;
        int[] columnWidths = new int[numOfAttributes];

        for (int i = 0; i < numOfAttributes; i++) {
            columnWidths[i] = Math.max(columnWidths[i], labels[i].length());
        }

        for (Student student : students) {
            String[] attributes = student.toString().split(",");

            for (int i = 0; i < numOfAttributes; i++) {
                columnWidths[i] = Math.max(columnWidths[i], attributes[i].length());
            }
        }

        return columnWidths;
    }

    private int[] getColumnWidthsOfCourses(ArrayList<Course> courses) {
        String[] labels = new String[]{"Course ID", "Name", "Credit"};

        int numOfAttributes = courses.get(0).toString().split(",").length;
        int[] columnWidths = new int[numOfAttributes];

        for (int i = 0; i < numOfAttributes; i++) {
            columnWidths[i] = Math.max(columnWidths[i], labels[i].length());
        }

        for (Course course : courses) {
            String[] attributes = course.toString().split(",");

            for (int i = 0; i < numOfAttributes; i++) {
                columnWidths[i] = Math.max(columnWidths[i], attributes[i].length());
            }
        }

        return columnWidths;
    }

    private int getBorderLength(int[] columnWidths) {
        int borderLength = 1;
        for (int width : columnWidths) {
            borderLength += width + 3;
        }

        return borderLength;
    }

    private void printLine(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
    }

    private void printCenter(int length, String field) {
        int left = (length - field.length()) / 2;
        int right = length - left - field.length();
        printLine(left, ' ');
        System.out.print(field);
        printLine(right, ' ');
    }

    private void printRow(String[] fields, int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            System.out.print("| ");
            printCenter(columnWidths[i], fields[i]);
            System.out.print(" ");
        }

        System.out.print("|");
        System.out.println();
    }

    private void printBorder(int[] columnWidths) {
        printLine(getBorderLength(columnWidths), '-');
        System.out.println();
    }

    @Override
    public void displayEnrollments(ArrayList<Enrollment> enrollmentList) {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        for (Enrollment enrollment : enrollmentList) {
            students.add(enrollment.getStudent());
            courses.add(enrollment.getCourse());
        }

        int[] studentColumnWidths = getColumnWidthsOfStudents(students);
        int[] courseColumnWidths = getColumnWidthsOfCourses(courses);
        int semesterColumnLength = "Semester".length();

        // merge 2 array of column widths together
        int[] columnWidths = new int[studentColumnWidths.length + courseColumnWidths.length + 1];
        int pos = 0;

        for (int width : studentColumnWidths) {
            columnWidths[pos] = width;
            pos++;
        }

        for (int width : courseColumnWidths) {
            columnWidths[pos] = width;
            pos++;
        }

        columnWidths[pos] = semesterColumnLength;

        // labels of the enrollment table
        String[] labels = new String[]{"Student ID", "Name", "Birthdate", "Course ID", "Name", "Credit", "Semester"};

        printCenter(getBorderLength(columnWidths), "STUDENT ENROLLMENT");
        System.out.println();

        printBorder(columnWidths);
        printRow(labels, columnWidths);
        printBorder(columnWidths);

        for (Enrollment enrollment : enrollmentList) {
            String[] attributes = enrollment.toString().split(",");
            printRow(attributes, columnWidths);
            printBorder(columnWidths);
        }
    }

    @Override
    public void displayStudents(ArrayList<Student> studentList) {
        int[] studentColumnWidths = getColumnWidthsOfStudents(studentList);

        // labels of the student table
        String[] labels = new String[]{"Student ID", "Name", "Birthdate"};

        printCenter(getBorderLength(studentColumnWidths), "STUDENT LIST");
        System.out.println();

        printBorder(studentColumnWidths);
        printRow(labels, studentColumnWidths);
        printBorder(studentColumnWidths);

        for (Student student : studentList) {
            String[] attributes = student.toString().split(",");
            printRow(attributes, studentColumnWidths);
            printBorder(studentColumnWidths);
        }
    }

    @Override
    public void displayCourses(ArrayList<Course> courseList) {
        int[] courseColumnWidths = getColumnWidthsOfCourses(courseList);

        // labels of the course table
        String[] labels = new String[]{"Course ID", "Name", "Credit"};

        printCenter(getBorderLength(courseColumnWidths), "COURSE LIST");
        System.out.println();

        printBorder(courseColumnWidths);
        printRow(labels, courseColumnWidths);
        printBorder(courseColumnWidths);

        for (Course course : courseList) {
            String[] attributes = course.toString().split(",");
            printRow(attributes, courseColumnWidths);
            printBorder(courseColumnWidths);
        }
    }

    public static void main(String[] args) {
        StudentEnrollmentManagerImpl studentEnrollmentManager = new StudentEnrollmentManagerImpl();
        studentEnrollmentManager.populateData();
        Display tableDisplay = new TableDisplay();
        tableDisplay.displayEnrollments(studentEnrollmentManager.getAll());
    }
}
