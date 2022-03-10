package com.br.levelup.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static com.br.levelup.model.SubCategory.*;

public class SubCategoryTest {

    private Category category = new Category("Programacao", "java");

    @Test
    void should_build_new_subcategory() {
        SubCategory subCategory = new SubCategory("Banco de dados", "sql-server", category);
        Assertions.assertNotNull(subCategory);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_exception_when_name_is_invalid(String name) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SubCategory(name, "java-a", category));
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "java1", "java 1"})
    void should_throw_exception_when_code_is_invalid(String code) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Programacao", code, category));
    }

    @ParameterizedTest
    @NullSource
    void should_throw_exception_when_subcategory_is_null(Category category) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SubCategory("Programacao", "java-oo", category));
    }

    @Test
    void should_retrieve_all_active_subcategories_sorted_by_order() {
        SubCategory activeSubCategory1 = new SubCategory("Programacao", "java-oo", category);
        SubCategory activeSubCategory2 = new SubCategory("Devops", "devops", category);
        SubCategory inactiveSubCategory = new SubCategory("Business", "renda-variavel", category);
        List<SubCategory> subCategories = List.of(activeSubCategory1, activeSubCategory2, inactiveSubCategory);

        activeSubCategory1.setActive(true);
        activeSubCategory2.setActive(true);
        activeSubCategory1.setOrder(1);
        activeSubCategory2.setOrder(2);

        List<SubCategory> activeSubCategoriesSortedByOrder = activeSubCategoriesSortedByOrder(subCategories);

        Assertions.assertFalse(activeSubCategoriesSortedByOrder.isEmpty());
        Assertions.assertEquals(subCategories.subList(0, 2), activeSubCategoriesSortedByOrder);
    }

    @Test
    void should_retrieve_the_total_of_active_subcategories_with_description() {
        SubCategory activeSubCategory1 = new SubCategory("Programacao", "java-oo", category);
        SubCategory activeSubCategory2 = new SubCategory("Devops", "devops", category);
        SubCategory inactiveSubCategory = new SubCategory("Business", "renda-variavel", category);
        SubCategory subCategoryWithNullDescription = new SubCategory("Programacao", "ruby", category);
        List<SubCategory> subCategories = List.of(activeSubCategory2, activeSubCategory1, inactiveSubCategory,
                subCategoryWithNullDescription);

        activeSubCategory1.setActive(true);
        activeSubCategory2.setActive(true);
        subCategoryWithNullDescription.setActive(true);
        activeSubCategory1.setOrder(2);
        activeSubCategory2.setOrder(1);
        subCategoryWithNullDescription.setOrder(3);
        activeSubCategory1.setShortDescription("description");
        activeSubCategory2.setShortDescription("description");

        long total = totalOfActiveSubCategoriesWithDescription(subCategories);

        Assertions.assertEquals(2, total);
    }

    @Test
    void should_retrieve_all_subcategories_without_description() {
        SubCategory subCategoryWithoutDescription1 = new SubCategory("Programacao", "java-oo", category);
        SubCategory subCategoryWithoutDescription2 = new SubCategory("Devops", "devops", category);
        SubCategory subCategoryWithDescription = new SubCategory("Business", "renda-variavel", category);
        SubCategory subCategoryWithNullDescription = new SubCategory("Programacao", "ruby", category);
        List<SubCategory> subCategories = List.of(subCategoryWithoutDescription1, subCategoryWithoutDescription2,
                subCategoryWithDescription, subCategoryWithNullDescription);

        subCategoryWithoutDescription1.setShortDescription("");
        subCategoryWithoutDescription2.setShortDescription("");
        subCategoryWithDescription.setShortDescription("description");

        List<SubCategory> subCategoriesWithoutDescription = subCategoriesWithoutDescription(subCategories);

        Assertions.assertTrue(subCategoriesWithoutDescription.size() == 3);
        Assertions.assertTrue(subCategoriesWithoutDescription.containsAll(List.of(subCategoryWithoutDescription1,
                subCategoryWithoutDescription2)));
        Assertions.assertFalse(subCategoriesWithoutDescription.contains(subCategoryWithDescription));
    }

    @ParameterizedTest
    @CsvSource({
            "'', 0",
            "10, 10"
    })
    void should_retrieve_correctly_order(String order, String expectedStringOrder) {
        Assertions.assertEquals(expectedStringOrder, processingOrder(order).toString());
    }

    @ParameterizedTest
    @CsvSource({
            "ATIVA, true",
            "INATIVA, false",
            "OUTRA COISA, false"
    })
    void should_retrieve_correctly_active(String active, boolean expectedActive) {
        Assertions.assertEquals(expectedActive, convertToBoolean(active));
    }

}
