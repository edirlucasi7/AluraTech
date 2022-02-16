package validators;

public class IntegerValidator {

    public static void cantBeLessOrEqualZeroOrNull(Integer field, String error) {
        if(field == null || field <= 0) {
            throw new IllegalArgumentException(error);
        }
    }

}
