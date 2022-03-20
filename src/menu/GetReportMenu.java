package menu;

import csv.CSVPrinter;
import model.Course;
import model.Student;
import repository.StudentEnrollmentManager;
import service.GetReportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetReportMenu extends Menu {
    public GetReportMenu(StudentEnrollmentManager studentEnrollmentManager) {
        super(studentEnrollmentManager,
                "GET REPORT MENU",
                new ArrayList<>(List.of("Get all courses of one student in a semester",
                        "Get all students of one course in a semester",
                        "Get all courses in a semester",
                        "Back")));
    }

    /**
     * A method to check if the result Course list exists and not empty
     * @param resultCourseList: the result Course list
     * @return boolean indicates whether it exists and not empty
     */
    private boolean checkCourseList(ArrayList<Course> resultCourseList) {
        if (resultCourseList == null) {
            System.out.println();
            return false;
        }

        if (resultCourseList.isEmpty()) {
            System.out.println("There is no result!\n");
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
            System.out.println();
            return false;
        }

        if (resultStudentList.isEmpty()) {
            System.out.println("There is no result!\n");
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
        System.out.println("Do you want to save the above report in a CSV file? (Y/N): ");
        String command = scanner.nextLine();
        if (command.equalsIgnoreCase("y")) {
            CSVPrinter csvPrinter = new CSVPrinter(courseID, semester);
            if (csvPrinter.writeStudents(resultStudentList)) {
                System.out.println("The report is saved in a CSV file!");
            }

            System.out.println("The CSV file can be found at " + csvPrinter.getDirectory());
        }
    }

    /**
     * A method to ask users if they want to save the list of courses to CSV file
     * @param studentID: the student ID of the report
     * @param semester: the semester of the report
     * @param resultCourseList: the list of courses of a student in one semester
     */
    private void askToSaveCoursesToCSV(String studentID, String semester, ArrayList<Course> resultCourseList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to save the above report in a CSV file? (Y/N): ");
        String command = scanner.nextLine();
        if (command.equalsIgnoreCase("y")) {
            CSVPrinter csvPrinter;
            if (studentID == null) {
                csvPrinter = new CSVPrinter(semester);
            } else {
                csvPrinter = new CSVPrinter(studentID, semester);
            }

            if (csvPrinter.writeCourses(resultCourseList)) {
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
        System.out.println("Do you want to add or delete new courses from the above list? (Y/N): ");
        String command = scanner.nextLine();
        if (command.equalsIgnoreCase("y")) {
            Menu updateEnrollmentMenu = new UpdateEnrollmentMenu(getStudentEnrollmentManager(), studentID, semester);
            updateEnrollmentMenu.processOptions();
            return true;
        }

        return false;
    }

    @Override
    public void processOptions() {
        GetReportService getReportService = new GetReportService(getStudentEnrollmentManager());
        while (true) {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            String studentID, courseID, semester;
            ArrayList<Course> resultCourseList;
            ArrayList<Student> resultStudentList;

            switch (option) {
                case "1":
                    System.out.println("Enter student ID: ");
                    studentID = scanner.nextLine();

                    System.out.println("Enter semester: ");
                    semester = scanner.nextLine();

                    resultCourseList = getReportService.getAllCoursesOfOneStudentOneSem(studentID, semester);

                    if (!checkCourseList(resultCourseList)) {
                        break;
                    }

                    System.out.println("All courses of student with ID: " + studentID + " in semester: " + semester);
                    for (Course course : resultCourseList) {
                        System.out.println(course);
                    }

                    if (askToUpdateEnrollment(studentID, semester)) {
                        resultCourseList = getReportService.getAllCoursesOfOneStudentOneSem(studentID, semester);
                        if (!checkCourseList(resultCourseList)) {
                            break;
                        } else {
                            System.out.println("The list of courses after update: ");
                            for (Course course : resultCourseList) {
                                System.out.println(course);
                            }
                        }
                    }

                    askToSaveCoursesToCSV(studentID, semester, resultCourseList);

                    System.out.println();
                    break;
                case "2":
                    System.out.println("Enter course ID: ");
                    courseID = scanner.nextLine();

                    System.out.println("Enter semester: ");
                    semester = scanner.nextLine();

                    resultStudentList = getReportService.getAllStudentsOfOneCourseOneSem(courseID, semester);
                    if (!checkStudentList(resultStudentList)) {
                        break;
                    }

                    System.out.println("All students of course with ID: " + courseID + " in semester: " + semester);
                    for (Student student : resultStudentList) {
                        System.out.println(student);
                    }

                    askToSaveStudentsToCSV(courseID, semester, resultStudentList);

                    System.out.println();
                    break;
                case "3":
                    System.out.println("Enter semester: ");
                    semester = scanner.nextLine();

                    resultCourseList = getReportService.getAllCoursesOfOneSemester(semester);
                    if (!checkCourseList(resultCourseList)) {
                        break;
                    }

                    System.out.println("All courses in semester: " + semester);
                    for (Course course : resultCourseList) {
                        System.out.println(course);
                    }

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
