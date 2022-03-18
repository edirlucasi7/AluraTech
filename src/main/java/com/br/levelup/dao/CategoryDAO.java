package com.br.levelup.dao;

import com.br.levelup.model.Category;

import javax.persistence.EntityManager;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Category category) {
        this.em.persist(category);
    }

}
