package com.br.levelup.model.utils;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeLessOrEqualZero;

public class EstimateValuesUtils {

    public static boolean minimumAndMaximumValue(Integer value, Integer minimum, Integer maximum) {
        cantBeLessOrEqualZero(value, "The field value should not be less than zero!");
        cantBeLessOrEqualZero(minimum, "The field value should not be less than zero!");
        cantBeLessOrEqualZero(maximum, "The field value should not be less than zero!");
        if(value.compareTo(minimum) >= 0 && value.compareTo(maximum) <= 0) {
            return true;
        }
        return false;
    }

}
