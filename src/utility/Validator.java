package utility;

import java.util.Date;

public final class Validator {
    private Validator(){};

    public static boolean checkDate(Date date) {
        return date != null;
    }

    public static boolean checkInteger(int num) {
        return num > 0;
    }

    public static boolean checkSemester(String semester) {
        return semester.matches("^(19|20)\\d{2}[A-C]$");
    }
}
