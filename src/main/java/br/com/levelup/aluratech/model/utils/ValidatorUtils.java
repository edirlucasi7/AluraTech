package br.com.levelup.aluratech.model.utils;

import java.util.regex.Pattern;

public class ValidatorUtils {

    private static final Pattern CODE_FORMAT = Pattern.compile("^[a-z-]*$");
    private static final Pattern CODE_FORMAT_WITH_NUMBER = Pattern.compile("^[a-z0-9-]*$");
    private static final Pattern CODE_COLOR_HEXADECIMAL_FORMAT = Pattern.compile("^#([a-fA-F0-9]){6}?$|^[\s]*$");

    public static void cantBeNull(Object object) {
        cantBeNull(object, "the object should not be null");
    }

    public static void cantBeNull(Object object, String errorMessage) {
        if(object == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void cantBeLessZero(Integer field) {
        cantBeLessZero(field, "The field value should not be less than zero!");
    }

    public static void cantBeLessZero(Integer field, String errorMessage) {
        if(field == null || field < 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void containOnlyLettersLowerCaseAndDash(String field) {
        containOnlyLettersLowerCaseAndDash(field, "The field code must not be out of lowercase letters and dash format!");
    }

    public static void containOnlyLettersLowerCaseAndDash(String field, String errorMessage) {
        if(field == null || !CODE_FORMAT.matcher(field).matches()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void containOnlyLettersLowerCaseAndNumbersAndDash(String field) {
        containOnlyLettersLowerCaseAndNumbersAndDash(field, "The field code must not be out of lowercase letters and numbers dash format!");
    }

    public static void containOnlyLettersLowerCaseAndNumbersAndDash(String field, String errorMessage) {
        if(field == null || !CODE_FORMAT_WITH_NUMBER.matcher(field).matches()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void cantBeNullOrEmpty(String field) {
        cantBeNullOrEmpty(field, "The field name should not be null or empty!");
    }

    public static void cantBeNullOrEmpty(String field, String errorMessage) {
        if(field == null || field.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void isHexadecimal(String field) {
        isHexadecimal(field, "The field codeColor should not be out of hexadecimal format!");
    }

    public static void isHexadecimal(String field, String errorMessage) {
        if(field == null || !CODE_COLOR_HEXADECIMAL_FORMAT.matcher(field).matches()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
