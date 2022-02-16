package validators;

public class ObjectValidator {

    public static void cantBeNull(Object object, String error) {
        if(object == null) {
            throw new IllegalArgumentException(error);
        }
    }

}
