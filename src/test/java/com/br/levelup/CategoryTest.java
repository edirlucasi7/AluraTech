package com.br.levelup;

import com.br.levelup.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static com.br.levelup.model.Category.*;

public class CategoryTest {

    @ParameterizedTest
    @NullAndEmptySource
    void should_return_invalid_name_argument(String name) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Category(name, "java-1"));
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "java1", "java 1"})
    void should_validate_code_invalid_argument(String code) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Category("Programacao", code));
    }

    @Test
    void should_return_order_equals_zero() {
        String stringOrder = "";
        Assertions.assertEquals(0, processingOrder(stringOrder));
    }

    @Test
    void should_return_order_equals_parameter() {
        String stringOrder = "10";
        Assertions.assertEquals(10, processingOrder(stringOrder));
    }

    @Test
    void should_retrieve_all_active_categories() {
        List<Category> expectedCategories = List.of(new Category("Programacao", "java-oo"),
                new Category("Banco de dados", "algebra-relacional"), new Category("UX", "design-visual"));

        expectedCategories.stream().forEach(c -> c.setActive(true));
        List<Category> activeCategories = activeCategories(expectedCategories);

        Assertions.assertNotNull(activeCategories);
        Assertions.assertEquals(expectedCategories, activeCategories);
    }

    @ParameterizedTest
    @CsvSource({
            "ATIVA, true",
            "INATIVA, false"
    })
    void should_return_correctly_active(String active, boolean expectedActive) {
        Assertions.assertEquals(expectedActive, convertToBoolean(active));
    }

}
