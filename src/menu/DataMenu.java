package menu;

import repository.StudentEnrollmentManagerImpl;
import utility.validator.SystemValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataMenu extends Menu{

    public DataMenu() {
        super("DATA MENU", new ArrayList<>(List.of("Your own sample CSV file", "Default CSV file", "Exit")));
    }

    @Override
    public void processOptions() {
        while (true) {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.println("Enter your CSV file name located at the directory src/files/data (does not require .csv at the end): ");
                    String fileName = scanner.nextLine().trim();
                    StudentEnrollmentManagerImpl user_semi = new StudentEnrollmentManagerImpl(fileName);
                    if (SystemValidator.checkData(user_semi)) {
                        this.setStudentEnrollmentManagerImpl(user_semi);
                        System.out.println();
                        return;
                    }

                    System.out.println();
                    break;
                case "2":
                    StudentEnrollmentManagerImpl default_semi = new StudentEnrollmentManagerImpl();
                    if (SystemValidator.checkData(default_semi)) {
                        this.setStudentEnrollmentManagerImpl(default_semi);
                        System.out.println();
                        return;
                    }

                    System.out.println();
                    break;
                case "3":
                    System.out.println("Program exits");
                    System.out.println("See you again!");
                    System.exit(0);
                default:
                    System.out.println("Not an option. Enter again!\n");
            }
        }
    }
}
