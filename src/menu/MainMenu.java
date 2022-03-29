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

        this.setStudentEnrollmentManagerImpl(dataMenu.getStudentEnrollmentManagerImpl());

        while (true) {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    Menu enrollmentMenu = new EnrollmentMenu(this.getStudentEnrollmentManagerImpl());
                    enrollmentMenu.processOptions();
                    break;
                case "2":
                    Menu getReportMenu = new GetReportMenu(this.getStudentEnrollmentManagerImpl());
                    getReportMenu.processOptions();
                    break;
                case "3":
                    System.out.println("Program exits!");
                    System.out.println("See you again!");
                    return;
                default:
                    System.out.println("Not an option. Enter again!\n");
            }
        }
    }
}
