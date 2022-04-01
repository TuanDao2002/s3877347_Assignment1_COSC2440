package menu;

import model.Course;
import repository.StudentEnrollmentManagerImpl;
import service.EnrollmentService;
import service.GetReportService;
import utility.display.Display;
import utility.display.TableDisplay;
import utility.input.InputGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateEnrollmentMenu extends Menu {
    private final String studentID;
    private final String semester;
    private final EnrollmentService enrollmentService;
    private final GetReportService getReportService;
    public UpdateEnrollmentMenu(StudentEnrollmentManagerImpl studentEnrollmentManagerImpl, String studentID, String semester) {
        super("UPDATE ENROLLMENT MENU",
                new ArrayList<>(List.of("View course list",
                        "Add new course",
                        "Delete course",
                        "Back")));
        this.studentID = studentID;
        this.semester = semester;
        this.enrollmentService = new EnrollmentService(studentEnrollmentManagerImpl);
        this.getReportService = new GetReportService(studentEnrollmentManagerImpl);
    }

    @Override
    public void processOptions() {
        Display tableDisplay = new TableDisplay();
        while (true) {
            System.out.println("\n*** For courses of student with ID: " + studentID + " in semester: " + semester + " ***");
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine().trim();

            String courseID;
            ArrayList<Course> courseList;
            switch (option) {
                case "1":
                    courseList = getReportService.getAllCoursesOfOneStudentOneSem(studentID, semester);

                    if (courseList == null) {
                        break;
                    }

                    if (courseList.isEmpty()) {
                        System.out.println("There is no result!");
                        break;
                    }

                    System.out.println("All courses of student with ID: " + studentID + " in semester: " + semester);
                    tableDisplay.displayCourses(courseList);
                    break;
                case "2":
                    courseID = InputGetter.getCourseID();

                    if (enrollmentService.addEnrollment(studentID, courseID, semester)) {
                        System.out.println("New enrollment is added");
                    }

                    break;
                case "3":
                    courseID = InputGetter.getCourseID();

                    if (enrollmentService.deleteEnrollment(studentID, courseID, semester)) {
                        System.out.println("The enrollment is deleted");
                    }

                    break;
                case "4":
                    return;
                default:
                    System.out.println("Not an option. Enter again!");
            }
        }
    }
}
