package utility.validator;

import java.util.Date;

public final class DataValidator {
    private DataValidator(){}

    /**
     * A method to validate a Date object
     * @param date: a Date object required to be validated
     * @return true if the Date object is not null
     */
    public static boolean checkDate(Date date) {
        return date != null;
    }

    /**
     * A method to validate a credit
     * @param num: a credit required to be validated
     * @return true if the credit is greater than zero
     */
    public static boolean checkCredit(int num) {
        return num > 0;
    }

    /**
     * A method to validate semester
     * @param semester: a semester required to be validated
     * @return true if the semester matches with regex format
     */
    public static boolean checkSemester(String semester) {
        return semester.matches("^(20)\\d{2}[A-C]$");
    }
}
