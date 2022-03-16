package utility;

public final class StringConverter {
    private StringConverter(){}

    public static int stringToInt(String integerString) {
        int num;
        try {
            num = Integer.parseInt(integerString);
        } catch (Exception e) {
            num = -1;
        }

        return num;
    }

    public static String intToString(int num) {
        return Integer.toString(num);
    }
}
