package utility.converter;

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
}
