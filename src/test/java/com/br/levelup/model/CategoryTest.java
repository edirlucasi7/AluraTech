package com.br.levelup.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.br.levelup.model.Category.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void should_build_new_category() {
        Category category = new Category("Devops", "dev-iniciante");
        assertNotNull(category);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_exception_when_name_is_invalid(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(name, "java-a"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_exception_when_code_is_invalid(String code) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programacao", code));
    }

    @ParameterizedTest
    @ValueSource(strings = {"!5D7B76", "#25", "1555#"})
    void should_throw_exception_when_colors_code_is_invalid(String colorCode) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programacao", "java-iniciante", colorCode));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#5D7B76", "#47B11F", "#A52C8E"})
    void should_retrieve_when_colors_code_is_valid(String colorCode) {
        Category category = new Category("Programacao", "java-iniciante", colorCode);
        assertNotNull(category);
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "java1", "java 1"})
    void should_validate_code_invalid_argument(String code) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("Programacao", code));
    }

    @ParameterizedTest
    @CsvSource({
            "'', 0",
            "10, 10"
    })
    void should_retrieve_correctly_order(String order, String expectedStringOrder) {
        assertEquals(expectedStringOrder, processingOrder(order).toString());
    }

    @Test
    void should_retrieve_all_active_categories() {
        Category activeCategory1 = new Category("Programacao", "java-oo");
        Category activeCategory2 = new Category("Banco de dados", "algebra-relacional");
        Category inactiveCategory = new Category("DevOps", "algebra-relacional");
        List<Category> categories = List.of(activeCategory1, activeCategory2, inactiveCategory);

        activeCategory1.setActive(true);
        activeCategory2.setActive(true);
        List<Category> activeCategories = activeCategories(categories);

        assertTrue(activeCategories.size() == 2);
        assertTrue(activeCategories.containsAll(List.of(activeCategory1, activeCategory2)));
        assertFalse(activeCategories.contains(inactiveCategory));
    }

    @ParameterizedTest
    @CsvSource({
            "ATIVA, true",
            "INATIVA, false",
            "OUTRA COISA, false"
    })
    void should_return_correctly_active(String active, boolean expectedActive) {
        assertEquals(expectedActive, convertToBoolean(active));
    }

}
