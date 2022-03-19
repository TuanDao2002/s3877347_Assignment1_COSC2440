package menu;

import repository.StudentEnrollmentManagerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataMenu extends Menu{

    public DataMenu(String menuName, ArrayList<String> options) {
        super(null,"DATA MENU", new ArrayList<>(List.of("Your own sample CSV file", "Default CSV file", "Exit")));
    }

    @Override
    public void processOptions() {
        Scanner scanner = new Scanner(System.in);
        displayOptions();
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Enter your CSV file name: ");
                String fileName = scanner.nextLine();
                super.setStudentEnrollmentManager(new StudentEnrollmentManagerImpl(fileName));
                break;
            case 2:
                break;
            default:
                System.out.println("Not an option. Enter again!\n");
                processOptions();
        }
    }
}
