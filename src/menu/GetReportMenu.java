package menu;

import csv.CSVPrinter;
import model.Course;
import model.Student;
import repository.StudentEnrollmentManagerImpl;
import service.GetReportService;
import utility.display.Display;
import utility.display.TableDisplay;
import utility.input.InputGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetReportMenu extends Menu {
    private final GetReportService getReportService;
    public GetReportMenu(StudentEnrollmentManagerImpl studentEnrollmentManagerImpl) {
        super(studentEnrollmentManagerImpl,
                "GET REPORT MENU",
                new ArrayList<>(List.of("Get all courses of one student in a semester",
                        "Get all students of one course in a semester",
                        "Get all courses in a semester",
                        "Back")));
        this.getReportService = new GetReportService(studentEnrollmentManagerImpl);
    }

    /**
     * A method to check if the result Course list exists and not empty
     * @param resultCourseList: the result Course list
     * @return boolean indicates whether it exists and not empty
     */
    private boolean checkCourseList(ArrayList<Course> resultCourseList) {
        if (resultCourseList == null) {
            return false;
        }

        if (resultCourseList.isEmpty()) {
            System.out.println("There is no result!");
            return false;
        }

        return true;
    }

    /**
     * A method to check if the result Student list exists and not empty
     * @param resultStudentList: the result Student list
     * @return boolean indicates whether it exists and not empty
     */
    private boolean checkStudentList(ArrayList<Student> resultStudentList) {
        if (resultStudentList == null) {
            return false;
        }

        if (resultStudentList.isEmpty()) {
            System.out.println("There is no result!");
            return false;
        }

        return true;
    }

    /**
     * A method to ask users if they want to save the list of students to CSV file
     * @param courseID: the course ID of the report
     * @param semester: the semester of the report
     * @param resultStudentList: the list of students of a course in one semester
     */
    private void askToSaveStudentsToCSV(String courseID, String semester, ArrayList<Student> resultStudentList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to save the above report in a CSV file? (Press y or Y to execute. Others to skip): ");
        String command = scanner.nextLine().trim();
        if (command.equalsIgnoreCase("y")) {
            CSVPrinter csvPrinter = new CSVPrinter(courseID, semester);
            if (csvPrinter.writeStudents(resultStudentList)) {
                System.out.println("The report is saved in a CSV file!");
            }

            System.out.println("The CSV file can be found at " + csvPrinter.getDirectory());
        }
    }

    /**
     * A method to ask users if they want to update enrollment for a student in one semester
     * @param studentID: the student ID of the update
     * @param semester: the semester of the update
     * @return boolean indicates that the update was executed
     */
    private boolean askToUpdateEnrollment(String studentID, String semester) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add or delete new courses from the above list? (Press y or Y to execute. Others to skip): ");
        String command = scanner.nextLine().trim();
        if (command.equalsIgnoreCase("y")) {
            Menu updateEnrollmentMenu = new UpdateEnrollmentMenu(getStudentEnrollmentManagerImpl(), studentID, semester);
            updateEnrollmentMenu.processOptions();
            return true;
        }

        return false;
    }

    /**
     * A method to ask users if they want to save the list of courses to CSV file
     * @param studentID: the student ID of the report
     * @param semester: the semester of the report
     * @param resultCourseList: the list of courses of a student in one semester
     */
    private void askToSaveCoursesToCSV(String studentID, String semester, ArrayList<Course> resultCourseList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to save the above report in a CSV file? (Press y or Y to execute. Others to skip): ");
        String command = scanner.nextLine().trim();
        if (command.equalsIgnoreCase("y")) {
            CSVPrinter csvPrinter;
            if (studentID == null) {
                csvPrinter = new CSVPrinter(semester);
            } else {
                csvPrinter = new CSVPrinter(studentID, semester);
            }

            if (csvPrinter.writeCourses(resultCourseList)) {
                System.out.println("The report is saved in a CSV file!");
                System.out.println("The CSV file can be found at " + csvPrinter.getDirectory());
            } else {
                System.out.println("Cannot save CSV file!");
            }
        }
    }

    @Override
    public void processOptions() {
        Display tableDisplay = new TableDisplay();
        while (true) {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine().trim();

            String studentID, courseID, semester;
            ArrayList<Course> resultCourseList;
            ArrayList<Student> resultStudentList;

            switch (option) {
                case "1":
                    studentID = InputGetter.getStudentID();
                    semester = InputGetter.getSemester();

                    resultCourseList = getReportService.getAllCoursesOfOneStudentOneSem(studentID, semester);
                    if (!checkCourseList(resultCourseList)) {
                        System.out.println();
                        break;
                    }

                    System.out.println("All courses of student with ID: " + studentID + " in semester: " + semester);
                    tableDisplay.displayCourses(resultCourseList);

                    boolean backFromUpdateMenu = false;
                    if (askToUpdateEnrollment(studentID, semester)) {
                        backFromUpdateMenu = true;
                        resultCourseList = getReportService.getAllCoursesOfOneStudentOneSem(studentID, semester);
                        if (!checkCourseList(resultCourseList)) {
                            System.out.println("All courses of student with ID: " + studentID + " in semester: " + semester + " is empty!");
                            System.out.println();
                            break;
                        } else {
                            System.out.println("All courses of student with ID: " + studentID + " in semester: " + semester + " after update");
                            tableDisplay.displayCourses(resultCourseList);
                        }
                    }

                    askToSaveCoursesToCSV(studentID, semester, resultCourseList);

                    if (backFromUpdateMenu) System.out.println("Back to Get Report menu!");

                    System.out.println();
                    break;
                case "2":
                    courseID = InputGetter.getCourseID();
                    semester = InputGetter.getSemester();

                    resultStudentList = getReportService.getAllStudentsOfOneCourseOneSem(courseID, semester);
                    if (!checkStudentList(resultStudentList)) {
                        System.out.println();
                        break;
                    }

                    System.out.println("All students of course with ID: " + courseID + " in semester: " + semester);
                    tableDisplay.displayStudents(resultStudentList);

                    askToSaveStudentsToCSV(courseID, semester, resultStudentList);

                    System.out.println();
                    break;
                case "3":
                    semester = InputGetter.getSemester();

                    resultCourseList = getReportService.getAllCoursesOfOneSemester(semester);
                    if (!checkCourseList(resultCourseList)) {
                        System.out.println();
                        break;
                    }

                    System.out.println("All courses in semester: " + semester);
                    tableDisplay.displayCourses(resultCourseList);

                    askToSaveCoursesToCSV(null, semester, resultCourseList);

                    System.out.println();
                    break;
                case "4":
                    System.out.println("Back to Main menu!\n");
                    return;
                default:
                    System.out.println("Not an option. Enter again!\n");
            }
        }
    }
}
