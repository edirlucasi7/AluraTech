package com.br.levelup.dao;

import com.br.levelup.model.Instructor;

import javax.persistence.EntityManager;

public class InstructorDAO {

    private EntityManager em;

    public InstructorDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Instructor instructor) {
        this.em.persist(instructor);
    }

}
