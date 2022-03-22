package com.br.levelup.dao;

import com.br.levelup.model.SubCategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubCategoryDAO {

    private EntityManager em;

    public SubCategoryDAO(EntityManager em) {
        this.em = em;
    }

    public SubCategory findByCode(String code) {
        String jpql = "SELECT s FROM SubCategory s WHERE s.code = :code";
        return em.createQuery(jpql, SubCategory.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    public List<SubCategory> getActiveSubCategoriesSortedByOrder() {
        return em.createQuery("SELECT s FROM SubCategory s WHERE s.active = true ORDER BY order_visualization", SubCategory.class)
                .getResultList();
    }

    public List<String> getNamesFromSubCategoriesWithoutDescription() {
        return em.createQuery(" SELECT s.name FROM SubCategory s WHERE short_description IS NULL OR short_description = ''", String.class)
                .getResultList();
    }

}
