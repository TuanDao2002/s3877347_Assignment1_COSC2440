package menu;

import repository.StudentEnrollmentManager;
import service.EnrollmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentMenu extends Menu{

    public EnrollmentMenu(StudentEnrollmentManager studentEnrollmentManager) {
        super(studentEnrollmentManager,
                "ENROLLMENT MENU",
                new ArrayList<>(List.of("View enrollments", "Add enrollment", "Delete enrollment", "Back")));
    }

    @Override
    public void processOptions() {
        EnrollmentService enrollmentService = new EnrollmentService(getStudentEnrollmentManager());
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
                    System.out.println("Enter student ID: ");
                    studentID = scanner.nextLine();

                    System.out.println("Enter course ID: ");
                    courseID = scanner.nextLine();

                    System.out.println("Enter semester: ");
                    semester = scanner.nextLine();

                    if (enrollmentService.addEnrollment(studentID, courseID, semester)) {
                        System.out.println("New enrollment is added");
                    }

                    System.out.println();
                    break;
                case "3":
                    System.out.println("Enter student ID: ");
                    studentID = scanner.nextLine();

                    System.out.println("Enter course ID: ");
                    courseID = scanner.nextLine();

                    System.out.println("Enter semester: ");
                    semester = scanner.nextLine();

                    if (enrollmentService.deleteEnrollment(studentID, courseID, semester)) {
                        System.out.println("The enrollment is deleted");
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
