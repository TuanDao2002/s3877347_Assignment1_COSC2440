package utility;

public final class StringConverter {
    private StringConverter(){}

    /**
     * A method to convert String to integer
     * @param integerString: the String required to be converted to integer
     * @return a new integer or -1 if the String has invalid integer format
     */
    public static int stringToInt(String integerString) {
        int num;
        try {
            num = Integer.parseInt(integerString);
        } catch (Exception e) {
            num = -1;
        }

        return num;
    }

    /**
     * A method to convert integer to String
     * @param num: the integer required to be converted to String
     * @return a new String object
     */
    public static String intToString(int num) {
        return Integer.toString(num);
    }
}
