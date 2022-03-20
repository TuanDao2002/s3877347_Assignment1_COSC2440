package menu;

import repository.StudentEnrollmentManager;
import service.EnrollmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateEnrollmentMenu extends Menu {
    private final String studentID;
    private final String semester;
    public UpdateEnrollmentMenu(StudentEnrollmentManager studentEnrollmentManager, String studentID, String semester) {
        super(studentEnrollmentManager,
                "UPDATE ENROLLMENT MENU",
                new ArrayList<>(List.of("Add new course",
                        "Delete course",
                        "Back")));
        this.studentID = studentID;
        this.semester = semester;
    }

    @Override
    public void processOptions() {
        EnrollmentService enrollmentService = new EnrollmentService(this.getStudentEnrollmentManager());
        while (true) {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            String courseID;
            switch (option) {
                case "1":
                    System.out.println("Enter course ID: ");
                    courseID = scanner.nextLine();

                    if (enrollmentService.addEnrollment(studentID, courseID, semester)) {
                        System.out.println("New enrollment is added");
                    }

                    System.out.println();
                    break;
                case "2":
                    System.out.println("Enter course ID: ");
                    courseID = scanner.nextLine();

                    if (enrollmentService.deleteEnrollment(studentID, courseID, semester)) {
                        System.out.println("The enrollment is deleted");
                    }

                    System.out.println();
                    break;
                case "3":
                    System.out.println("Back to Get Report menu!\n");
                    return;
                default:
                    System.out.println("Not an option. Enter again!\n");
            }
        }
    }
}
