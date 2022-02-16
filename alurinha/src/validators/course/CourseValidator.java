package validators.course;

import static com.br.levelup.model.utils.EstimateValuesUtils.minimumAndMaximumValue;

public class CourseValidator {

    private static final Integer STIMATEDTIMEMIN = 1;
    private static final Integer STIMATEDTIMEMAX = 20;

    public static void isBetween(Integer field, String error) {
        if(!minimumAndMaximumValue(field, STIMATEDTIMEMIN, STIMATEDTIMEMAX)) {
            throw new IllegalArgumentException(error);
        }
    }

}
