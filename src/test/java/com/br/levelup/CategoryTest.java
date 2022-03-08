package com.br.levelup;

import com.br.levelup.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.br.levelup.model.Category.*;
import static com.br.levelup.model.SubCategory.processingOrder;

public class CategoryTest {

    @Test
    void should_add_new_category() {
        Category category = new Category("Devops", "dev-iniciante");

        Assertions.assertNotNull(category);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_retrieve_invalid_name_argument(String name) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Category(name, "java-1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!5D7B76", "#25", "1555#"})
    void should_retrieve_color_code_argument(String colorCode) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Category("Programacao", "java-iniciante", colorCode));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#5D7B76", "#47B11F", "#A52C8E"})
    void should_retrieve_invalid_color_code_argument(String colorCode) {
        Category category = new Category("Programacao", "java-iniciante", colorCode);
        Assertions.assertNotNull(category);
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "java1", "java 1"})
    void should_validate_code_invalid_argument(String code) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Category("Programacao", code));
    }

    @ParameterizedTest
    @CsvSource({
            "'', 0",
            "10, 10"
    })
    void should_retrieve_correctly_order(String order, String expectedStringOrder) {
        Assertions.assertEquals(expectedStringOrder, processingOrder(order).toString());
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
            "INATIVA, false",
            "OUTRA COISA, false"
    })
    void should_return_correctly_active(String active, boolean expectedActive) {
        Assertions.assertEquals(expectedActive, convertToBoolean(active));
    }

}
