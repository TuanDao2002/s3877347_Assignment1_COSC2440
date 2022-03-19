package menu;

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
                    if (resultCourseList == null) {
                        System.out.println();
                        break;
                    }

                    if (resultCourseList.isEmpty()) {
                        System.out.println("There is no result!\n");
                        break;
                    }

                    System.out.println("All courses of student with ID: " + studentID + " in semester: " + semester);
                    for (Course course : resultCourseList) {
                        System.out.println(course);
                    }

                    System.out.println();
                    break;
                case "2":
                    System.out.println("Enter course ID: ");
                    courseID = scanner.nextLine();

                    System.out.println("Enter semester: ");
                    semester = scanner.nextLine();

                    resultStudentList = getReportService.getAllStudentsOfOneCourseOneSem(courseID, semester);
                    if (resultStudentList == null) {
                        System.out.println();
                        break;
                    }

                    if (resultStudentList.isEmpty()) {
                        System.out.println("There is no result!\n");
                        break;
                    }

                    System.out.println("All students of course with ID: " + courseID + " in semester: " + semester);
                    for (Student student : resultStudentList) {
                        System.out.println(student);
                    }

                    System.out.println();
                    break;
                case "3":
                    System.out.println("Enter semester: ");
                    semester = scanner.nextLine();

                    resultCourseList = getReportService.getAllCoursesOfOneSemester(semester);
                    if (resultCourseList == null) {
                        System.out.println();
                        break;
                    }

                    if (resultCourseList.isEmpty()) {
                        System.out.println("There is no result!\n");
                        break;
                    }

                    System.out.println("All courses in semester: " + semester);
                    for (Course course : resultCourseList) {
                        System.out.println(course);
                    }

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
