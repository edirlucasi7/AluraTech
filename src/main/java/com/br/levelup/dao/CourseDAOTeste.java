package com.br.levelup.dao;

import com.br.levelup.model.Course;

import javax.persistence.EntityManager;

public class CourseDAOTeste {

    private EntityManager em;

    public CourseDAOTeste(EntityManager em) {
        this.em = em;
    }

    public void create(Course course) {
        this.em.persist(course);
    }

}
