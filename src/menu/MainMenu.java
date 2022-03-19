package menu;

import repository.StudentEnrollmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu(StudentEnrollmentManager studentEnrollmentManager) {
       super(studentEnrollmentManager, "MAIN MENU", new ArrayList<>(List.of("Manage enrollments", "Manage students", "Manage courses", "Exit")));
    }

    @Override
    public void processOptions() {
        displayOptions();
        System.out.print("Option: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                break;
            case 2:
                System.out.println("Option 2");
                break;
            case 3:
                System.out.println("Option 3");
                break;
            case 4:
                System.out.println("Program exits!");
                break;
            default:
                System.out.println("Not an option. Enter again!\n");
                processOptions();
        }
    }
}
