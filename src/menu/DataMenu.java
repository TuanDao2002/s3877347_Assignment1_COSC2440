package menu;

import repository.StudentEnrollmentManagerImpl;
import utility.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataMenu extends Menu{

    public DataMenu() {
        super("DATA MENU", new ArrayList<>(List.of("Your own sample CSV file", "Default CSV file", "Exit")));
    }

    @Override
    public void processOptions() {
        String option;

        do {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Enter your CSV file name in files folder (does not require .csv at the end): ");
                    String fileName = scanner.nextLine();
                    StudentEnrollmentManagerImpl user_semi = new StudentEnrollmentManagerImpl(fileName);
                    if (Validator.checkData(user_semi)) {
                        this.setStudentEnrollmentManager(user_semi);
                    } else {
                        option = null;
                    }
                    break;
                case "2":
                    StudentEnrollmentManagerImpl default_semi = new StudentEnrollmentManagerImpl();
                    if (Validator.checkData(default_semi)) {
                        this.setStudentEnrollmentManager(default_semi);
                    } else {
                        option = null;
                    }
                    break;
                case "3":
                    System.out.println("Program exits");
                    System.exit(0);
                default:
                    System.out.println("Not an option. Enter again!\n");
                    option = null;
            }
        } while (option == null);
    }
}
