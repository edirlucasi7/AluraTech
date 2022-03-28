package com.br.levelup.dao;

import com.br.levelup.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Category category) {
        this.em.persist(category);
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(em.createQuery("SELECT c FROM Category c WHERE c.id = :id", Category.class)
                .setParameter("id", id)
                .getSingleResult());
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
