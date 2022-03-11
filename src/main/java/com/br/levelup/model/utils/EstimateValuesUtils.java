package com.br.levelup.model.utils;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeLessZero;
import static com.br.levelup.model.utils.ValidatorUtils.cantBeNull;

public class EstimateValuesUtils {

    public static boolean minimumAndMaximumValue(Integer value, Integer minimum, Integer maximum) {
        cantBeLessZero(value, "The field value should not be less than zero!");
        cantBeLessZero(minimum, "The field value should not be less than zero!");
        cantBeLessZero(maximum, "The field value should not be less than zero!");
        cantBeNull(minimum);
        cantBeNull(maximum);
        if(value.compareTo(minimum) >= 0 && value.compareTo(maximum) <= 0) {
            return true;
        }
        return false;
    }

}
