package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateConverter {
    private static final String datePattern = "MM/dd/yyyy";
    private DateConverter(){}

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(dateString);
    }

    public static String dateToString(Date date) {
        return new SimpleDateFormat(datePattern).format(date);
    }
}
