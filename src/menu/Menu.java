package menu;

import repository.StudentEnrollmentManager;

import java.util.ArrayList;

public abstract class Menu {
    private StudentEnrollmentManager studentEnrollmentManager;
    private final String menuName;
    private final ArrayList<String> options;

    public Menu(StudentEnrollmentManager studentEnrollmentManager, String menuName, ArrayList<String> options) {
        this.studentEnrollmentManager = studentEnrollmentManager;
        this.menuName = menuName;
        this.options = options;
    }

    public Menu(String menuName, ArrayList<String> options) {
        this.menuName = menuName;
        this.options = options;
    }

    public void setStudentEnrollmentManager(StudentEnrollmentManager studentEnrollmentManager) {
        this.studentEnrollmentManager = studentEnrollmentManager;
    }

    public StudentEnrollmentManager getStudentEnrollmentManager() {
        return studentEnrollmentManager;
    }

    private void printLine(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
    }

    private void printCenter(int length, String menuName) {
        int left = (length - menuName.length()) / 2;
        int right = length - left - menuName.length();
        printLine(left, ' ');
        System.out.print(menuName);
        printLine(right, ' ');
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
        int length = 50;
        displayMenuName(length, menuName);

        System.out.println("Choose an option");
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ": " + options.get(i));
        }

        printLine(length, '-');
        System.out.println();
        System.out.print("Option: ");
    }

    public abstract void processOptions();
}
