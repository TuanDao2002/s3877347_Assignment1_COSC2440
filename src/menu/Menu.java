package menu;

import repository.StudentEnrollmentManager;

import java.util.ArrayList;

public abstract class Menu {
    private final StudentEnrollmentManager studentEnrollmentManager;
    private String menuName;
    private ArrayList<String> options;
    private static int length = 50;

    public Menu(StudentEnrollmentManager studentEnrollmentManager, String menuName, ArrayList<String> options) {
        this.studentEnrollmentManager = studentEnrollmentManager;
        this.menuName = menuName;
        this.options = options;
    }

    private void printLine(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
    }

    private void printCenter(int length, String menuName) {
        printLine((length - menuName.length()) / 2 - 1, ' ');
        System.out.print(menuName);
        printLine((length - menuName.length()) / 2, ' ');
    }

    private void displayMenuName(int length, String menuName) {
        printLine(length, '-');
        System.out.println();
        printCenter(length, menuName);
        System.out.println();
        printLine(length, '-');
        System.out.println();
    }

    public void displayOptions() {
        displayMenuName(length, menuName);

        System.out.println("Choose an option");
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ": " + options.get(i));
        }

        printLine(length, '-');
        System.out.println();
    }

    public abstract void processOptions();
}
