package validators;

import java.util.regex.Pattern;

public class StringValidator {

    private static final Pattern CODEFORMAT = Pattern.compile("^[a-z0-9-]*$");
    private static final Pattern CODECOLORHEXADECIMALFOTMAT = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

    public static void containOnlyLettersLowercaseAndNumbersAndDash(String field, String error) {
        if(!CODEFORMAT.matcher(field).matches()) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void cantBeNotEmpty(String field, String error) {
        if(field.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void cantBeNull(String field, String error) {
        if(field == null) {
            throw new NullPointerException(error);
        }
    }

    public static void isHexadecimal(String field, String error) {
        if(!CODECOLORHEXADECIMALFOTMAT.matcher(field).matches()) {
            throw new IllegalArgumentException(error);
        }
    }

}
