package com.br.levelup.model.utils;

import static validators.IntegerValidator.cantBeLessOrEqualZeroOrNull;

public class EstimateValuesUtils {

    public static boolean minimumAndMaximumValue(Integer value, Integer minimum, Integer maximum) {
        cantBeLessOrEqualZeroOrNull(value, "The field value should not be less than zero or null!");
        cantBeLessOrEqualZeroOrNull(minimum, "The field minimum should not be less than zero or null!");
        cantBeLessOrEqualZeroOrNull(maximum, "The field maximum should not be less than zero or null!");
        if(value.compareTo(minimum) >= 0 && value.compareTo(maximum) <= 0) {
            return true;
        }
        return false;
    }

}
