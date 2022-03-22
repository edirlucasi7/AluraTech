package com.br.levelup.dao;

import com.br.levelup.model.Category;
import com.br.levelup.model.utils.builders.CategoryBuilder;
import com.br.levelup.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAOTest {

    private CategoryDAO categoryDAO;
    private EntityManager manager;

    @BeforeEach
    public void beforeEach() {
        this.manager = JPAUtil.getEntityManager();
        this.categoryDAO = new CategoryDAO(manager);
        manager.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        manager.getTransaction().rollback();
    }

    @Test
    void should_retrieve_all_active_categories_sorted_by_order() {
        Category activeCategory1 = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java")
                .toEntity();
        activeCategory1.setOrder(1);
        activeCategory1.setActive(true);

        Category activeCategory2 = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .toEntity();
        activeCategory2.setOrder(2);
        activeCategory2.setActive(true);

        Category inactiveCategory = new CategoryBuilder()
                .withName("Devops")
                .withCode("docker")
                .toEntity();

        manager.persist(activeCategory1);
        manager.persist(activeCategory2);
        manager.persist(inactiveCategory);

        List<Category> activeCategoriesSorted = categoryDAO.getActiveCategoriesSorted();

        Assertions.assertTrue(activeCategoriesSorted.size() == 2);
        Assertions.assertTrue(activeCategoriesSorted.containsAll(List.of(activeCategory1, activeCategory2)));
        Assertions.assertFalse(activeCategoriesSorted.contains(inactiveCategory));

    }

}
