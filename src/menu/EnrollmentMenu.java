package menu;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentMenu extends Menu{

    public EnrollmentMenu() {
        super("ENROLLMENT MENU", new ArrayList<>(List.of("View enrollments", "Add enrollment", "Delete enrollment", "Exit")));
    }

    @Override
    public void processOptions() {

    }
}
