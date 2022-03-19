package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu() {
       super("MAIN MENU", new ArrayList<>(List.of("Manage enrollments", "Get report", "Exit")));
    }

    @Override
    public void processOptions() {
        // show the data menu for users to choose which CSV file to populate data
        Menu dataMenu = new DataMenu();
        dataMenu.processOptions();

        this.setStudentEnrollmentManager(dataMenu.getStudentEnrollmentManager());
        String option;
        do {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    Menu enrollmentMenu = new EnrollmentMenu(this.getStudentEnrollmentManager());
                    enrollmentMenu.processOptions();
                    break;
                case "2":
                    System.out.println("Option 2");
                    break;
                case "3":
                    System.out.println("Program exits!");
                    break;
                default:
                    System.out.println("Not an option. Enter again!\n");
                    option = null;
            }
        } while (option == null);

    }

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.processOptions();
    }
}
