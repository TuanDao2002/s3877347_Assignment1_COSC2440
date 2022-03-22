package menu;

import repository.StudentEnrollmentManager;
import service.EnrollmentService;
import utility.input.InputGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateEnrollmentMenu extends Menu {
    private final String studentID;
    private final String semester;
    private final EnrollmentService enrollmentService;
    public UpdateEnrollmentMenu(StudentEnrollmentManager studentEnrollmentManager, String studentID, String semester) {
        super("UPDATE ENROLLMENT MENU",
                new ArrayList<>(List.of("Add new course",
                        "Delete course",
                        "Back")));
        this.studentID = studentID;
        this.semester = semester;
        this.enrollmentService = new EnrollmentService(studentEnrollmentManager);
    }

    @Override
    public void processOptions() {
        while (true) {
            System.out.println("\nFor courses of student with ID: " + studentID + " in semester: " + semester);
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            String courseID;
            switch (option) {
                case "1":
                    courseID = InputGetter.getCourseID();

                    if (enrollmentService.addEnrollment(studentID, courseID, semester)) {
                        System.out.println("New enrollment is added");
                    }

                    break;
                case "2":
                    courseID = InputGetter.getCourseID();

                    if (enrollmentService.deleteEnrollment(studentID, courseID, semester)) {
                        System.out.println("The enrollment is deleted");
                    }

                    break;
                case "3":
                    return;
                default:
                    System.out.println("Not an option. Enter again!");
            }
        }
    }
}
