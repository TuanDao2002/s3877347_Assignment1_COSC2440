package menu;

import repository.StudentEnrollmentManager;
import service.EnrollmentService;
import utility.input.InputGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentMenu extends Menu{
    private final EnrollmentService enrollmentService;

    public EnrollmentMenu(StudentEnrollmentManager studentEnrollmentManager) {
        super("ENROLLMENT MENU",
                new ArrayList<>(List.of("View enrollments", "Add enrollment", "Delete enrollment", "Back")));
        this.enrollmentService = new EnrollmentService(studentEnrollmentManager);
    }

    @Override
    public void processOptions() {
        while (true) {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            String studentID, courseID, semester;

            switch (option) {
                case "1":
                    enrollmentService.displayAll();
                    System.out.println();
                    break;
                case "2":
                    studentID = InputGetter.getStudentID();
                    courseID = InputGetter.getCourseID();
                    semester = InputGetter.getSemester();

                    if (enrollmentService.addEnrollment(studentID, courseID, semester)) {
                        System.out.println("New enrollment is added\n");
                        break;
                    }

                    System.out.println();
                    break;
                case "3":
                    studentID = InputGetter.getStudentID();
                    courseID = InputGetter.getCourseID();
                    semester = InputGetter.getSemester();

                    if (enrollmentService.deleteEnrollment(studentID, courseID, semester)) {
                        System.out.println("The enrollment is deleted\n");
                        break;
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
