package com.br.levelup.model.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    void should_validate_estimated_time(Integer estimatedTimeInHours, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, minimumAndMaximumValue(estimatedTimeInHours, ESTIMATED_IN_HOURS_MIN,
                ESTIMATED_IN_HOURS_MAX));
    }

    @Test
    void should_throw_exception_when_minimum_and_maximum_of_estimated_value_is_null() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  minimumAndMaximumValue(10, null,
                        null));
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "23", "24", "25"})
    void should_throw_exception_when_minimum_and_maximum_of_estimated_value_is_less_than_zero(Integer estimatedTimeInHours) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  minimumAndMaximumValue(estimatedTimeInHours, -1,
                        0));
    }

}
