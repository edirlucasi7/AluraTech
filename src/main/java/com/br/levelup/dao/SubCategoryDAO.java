package com.br.levelup.dao;

import com.br.levelup.model.SubCategory;

import javax.persistence.EntityManager;

public class SubCategoryDAO {

    private EntityManager em;

    public SubCategoryDAO(EntityManager em) {
        this.em = em;
    }

    public void create(SubCategory subCategory) {
        this.em.persist(subCategory);
    }

}
