package validators;

public class EnumValidator {

    public static void cantBeNull(Enum field, String error) {
        if(field == null) {
            throw new IllegalArgumentException(error);
        }
    }

}
