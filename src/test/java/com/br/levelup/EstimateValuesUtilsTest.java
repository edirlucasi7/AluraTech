package com.br.levelup;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.br.levelup.model.utils.EstimateValuesUtils.minimumAndMaximumValue;

public class EstimateValuesUtilsTest {

    private static final int ESTIMATED_IN_HOURS_MIN = 1;
    private static final int ESTIMATED_IN_HOURS_MAX = 24;

    @ParameterizedTest
    @CsvSource({
            "0, false", "1, true",
            "2, true", "23, true",
            "24, true", "25, false"
    })
    void should_validate_estimated_time_in_hours(Integer estimatedTimeInHours, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, minimumAndMaximumValue(estimatedTimeInHours, ESTIMATED_IN_HOURS_MIN,
                ESTIMATED_IN_HOURS_MAX));
    }

}
