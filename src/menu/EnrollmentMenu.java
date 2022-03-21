package menu;

import repository.StudentEnrollmentManager;
import service.EnrollmentService;
import utility.input.InputGetter;

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
                    studentID = InputGetter.getStudentID();
                    courseID = InputGetter.getCourseID();
                    semester = InputGetter.getSemester();

                    if (enrollmentService.addEnrollment(studentID, courseID, semester)) {
                        System.out.println("New enrollment is added\n");
                    }

                    System.out.println();
                    break;
                case "3":
                    studentID = InputGetter.getStudentID();
                    courseID = InputGetter.getCourseID();
                    semester = InputGetter.getSemester();

                    if (enrollmentService.deleteEnrollment(studentID, courseID, semester)) {
                        System.out.println("The enrollment is deleted\n");
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
