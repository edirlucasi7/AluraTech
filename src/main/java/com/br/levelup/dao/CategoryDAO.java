package com.br.levelup.dao;

import com.br.levelup.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public List<Category> getDataFromActiveCategories() {
        return em.createQuery("SELECT c FROM Category c WHERE c.active = true ORDER BY order_visualization", Category.class)
                .getResultList();
    }

}
