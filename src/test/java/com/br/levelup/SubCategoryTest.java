package com.br.levelup;

import com.br.levelup.model.Category;
import com.br.levelup.model.SubCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.br.levelup.model.SubCategory.*;

public class SubCategoryTest {

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

    @ParameterizedTest
    @NullSource
    void should_validate_category_null_argument(Category category) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Programacao", "java-oo", category));
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
    void should_retrieve_all_active_subcategories_sorted_by_order() {
        Category category = new Category("Programacao", "java");
        List<SubCategory> expectedSubCategories = List.of(new SubCategory("Programacao", "java-oo", category),
                new SubCategory("Banco de dados", "algebra-relacional", category), new SubCategory("UX",
                        "design-visual", category));
        AtomicInteger counter = new AtomicInteger();

        expectedSubCategories.stream().forEach(s -> s.setActive(true));
        expectedSubCategories.stream().forEach(s -> s.setOrder(counter.getAndIncrement()));
        List<SubCategory> activeSubCategories = activeSubCategoriesSortedByOrder(expectedSubCategories);

        Assertions.assertNotNull(activeSubCategories);
        Assertions.assertEquals(expectedSubCategories, activeSubCategories);
    }

    @Test
    void should_retrieve_the_total_of_active_subcategories_with_description() {
        Category category = new Category("Programacao", "java");
        List<SubCategory> subCategories = List.of(new SubCategory("Programacao", "java-oo", category),
                new SubCategory("UX", "design-visual", category));
        AtomicInteger counter = new AtomicInteger();

        subCategories.stream().forEach(s -> s.setActive(true));
        subCategories.stream().forEach(s -> s.setOrder(counter.getAndIncrement()));
        subCategories.stream().forEach(s -> s.setShortDescription("description"));
        long total = totalOfActiveSubCategoriesWithDescription(subCategories);

        Assertions.assertEquals(2, total);
    }

    @Test
    void should_retrieve_all_subcategories_without_description() {
        Category category = new Category("Programacao", "java");
        List<SubCategory> subCategoriesWithoutDescription = List.of(new SubCategory("Programacao", "java-oo", category),
                new SubCategory("Banco de dados", "algebra-relacional", category), new SubCategory("UX",
                        "design-visual", category));

        subCategoriesWithoutDescription.stream().forEach(s -> s.setShortDescription(""));
        List<SubCategory> activeSubCategories = subCategoriesWithoutDescription(subCategoriesWithoutDescription);

        Assertions.assertNotNull(activeSubCategories);
        Assertions.assertEquals(subCategoriesWithoutDescription, activeSubCategories);
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
