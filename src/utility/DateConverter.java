package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateConverter {
    private static final String datePattern = "MM/dd/yyyy";
    private DateConverter(){}

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

    public static String dateToString(Date date) {
        return new SimpleDateFormat(datePattern).format(date);
    }
}
