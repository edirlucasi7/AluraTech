package com.br.levelup.dao;

import com.br.levelup.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public List<Category> getActiveCategoriesSorted() {
        return em.createQuery("SELECT c FROM Category c WHERE c.active = true ORDER BY order_visualization", Category.class)
                .getResultList();
    }

    public List<Category> getAllCategories() {
        return em.createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }
}
