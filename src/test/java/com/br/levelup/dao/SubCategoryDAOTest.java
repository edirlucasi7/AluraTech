package com.br.levelup.dao;

import com.br.levelup.model.Category;
import com.br.levelup.model.SubCategory;
import com.br.levelup.model.utils.builders.CategoryBuilder;
import com.br.levelup.model.utils.builders.SubCategoryBuilder;
import com.br.levelup.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubCategoryDAOTest {

    private SubCategoryDAO subCategoryDAO;
    private EntityManager manager;
    private Category category;

    @BeforeEach
    public void beforeEach() {
        this.manager = JPAUtil.getEntityManager("tests");
        this.subCategoryDAO = new SubCategoryDAO(manager);
        manager.getTransaction().begin();
        this.category = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .toEntity();
        manager.persist(category);
    }

    @AfterEach
    public void afterEach() {
        manager.getTransaction().rollback();
    }

    @Test
    void should_retrieve_all_active_subcategories_sorted_by_order() {
        SubCategory activeSubCategory1 = new SubCategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .withOrder(1)
                .withActive(true)
                .withCategory(category)
                .toEntity();

        SubCategory activeSubCategory2 = new SubCategoryBuilder()
                .withName("Programacao")
                .withCode("devops")
                .withOrder(2)
                .withActive(true)
                .withCategory(category)
                .toEntity();

        SubCategory inactiveSubCategory = new SubCategoryBuilder()
                .withName("Programacao")
                .withCode("programacao")
                .withCategory(category)
                .toEntity();

        manager.persist(activeSubCategory1);
        manager.persist(activeSubCategory2);
        manager.persist(inactiveSubCategory);

        List<SubCategory> activeSubCategoriesSortedByOrder = subCategoryDAO.getActiveSubCategoriesSortedByOrder();

        assertTrue(activeSubCategoriesSortedByOrder.size() == 2);
        assertTrue(activeSubCategoriesSortedByOrder.containsAll(List.of(activeSubCategory1, activeSubCategory2)));
        assertFalse(activeSubCategoriesSortedByOrder.contains(inactiveSubCategory));
    }

    @Test
    void should_retrieve_all_subcategory_names_without_description() {
        SubCategory subCategoryWithNullDescription = new SubCategoryBuilder()
                .withName("Java")
                .withCode("java-iniciante")
                .withCategory(category)
                .toEntity();

        SubCategory subCategoryWithoutDescription2 = new SubCategoryBuilder()
                .withName("Php")
                .withCode("php-iniciante")
                .withShorDescription("")
                .withCategory(category)
                .toEntity();

        SubCategory subCategoryWithDescription = new SubCategoryBuilder()
                .withName("Programacao")
                .withCode("devops")
                .withCategory(category)
                .toEntity();
        subCategoryWithDescription.setShortDescription("pequena descricao");

        manager.persist(subCategoryWithNullDescription);
        manager.persist(subCategoryWithoutDescription2);
        manager.persist(subCategoryWithDescription);

        List<String> subCategoriesWithoutDescription = subCategoryDAO.getNamesFromSubCategoriesWithoutDescription();

        assertTrue(subCategoriesWithoutDescription.size() == 2);
        assertTrue(subCategoriesWithoutDescription.containsAll(List.of("Java", "Php")));
        assertFalse(subCategoriesWithoutDescription.contains(subCategoryWithDescription));
    }

}
