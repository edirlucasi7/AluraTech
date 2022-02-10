package validators;

public class CourseValidator {

    private static final Integer STIMATEDTIMEMIN = 1;
    private static final Integer STIMATEDTIMEMAX = 20;

    public static void isBetween(Integer field, String error) {
        if(!(field >= STIMATEDTIMEMIN && field <= STIMATEDTIMEMAX)) {
            throw new IllegalArgumentException(error);
        }
    }

}
