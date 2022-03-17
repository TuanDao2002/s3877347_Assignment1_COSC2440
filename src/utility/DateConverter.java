package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateConverter {
    private static final String datePattern = "MM/dd/yyyy";
    private DateConverter(){}

    /**
     * A method to convert String to Date
     * @param dateString: the String required to be converted to Date
     * @return a new Date object or null if the String has invalid date format
     */
    public static Date stringToDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        simpleDateFormat.setLenient(false);

        Date newDateObj;
        try{
           newDateObj = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            newDateObj = null;
        }

        return newDateObj;
    }

    /**
     * A method to convert Date to String
     * @param date: the Date required to be converted to String
     * @return a new String object with MM/dd/yyyy pattern
     */
    public static String dateToString(Date date) {
        return new SimpleDateFormat(datePattern).format(date);
    }
}
