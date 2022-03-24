package com.br.levelup.dao;

import com.br.levelup.model.Instructor;

import javax.persistence.EntityManager;

public class InstructorDAO {

    private EntityManager em;

    public InstructorDAO(EntityManager em) {
        this.em = em;
    }

    public Instructor findByName(String name) {
        String jpql = "SELECT i FROM Instructor i WHERE i.name = :name";
        return em.createQuery(jpql, Instructor.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
