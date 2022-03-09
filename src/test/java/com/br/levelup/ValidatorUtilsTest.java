package com.br.levelup;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.br.levelup.model.utils.ValidatorUtils.*;

public class ValidatorUtilsTest {

    @ParameterizedTest
    @NullSource
    void should_throw_exception_when_field_is_null(String field) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> cantBeNull(field));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_exception_when_field_is_null_and_empty(String field) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> cantBeNullOrEmpty(field));
    }

    @Test
    void should_throw_exception_when_field_is_less_zero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> cantBeLessZero(-1));
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "java1", "java 1"})
    void should_throw_exception_when_code_format_is_invalid(String field) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> containOnlyLettersLowercaseAndDash(field));
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "ja)va(", "java!#"})
    void should_throw_exception_when_code_format_with_numbers_is_invalid(String field) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> containOnlyLettersLowercaseAndDash(field));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!5D7B76", "#25", "1555#"})
    void should_throw_exception_when_hexadecimal_is_invalid(String field) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> containOnlyLettersLowercaseAndDash(field));
    }

}
