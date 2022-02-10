package validators;

import java.util.regex.Pattern;

public class StringValidator {

    private static final Pattern CODEFORMAT = Pattern.compile("^[a-z0-9-]*$");

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

    public static void cantBeNullOrEmpty(String name, String error) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
    }

}
