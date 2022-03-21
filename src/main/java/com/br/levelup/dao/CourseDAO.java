package com.br.levelup.dao;

import com.br.levelup.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CourseDAO {

    private EntityManager em;

    public CourseDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Course course) {
        this.em.persist(course);
    }

    public void deleteByCode(String code) {
        Query query = em.createQuery("DELETE FROM Course WHERE code = :code")
                .setParameter("code", code);
        query.executeUpdate();
    }

    public void updatePublicVisibility() {
        Query query = em.createQuery("UPDATE Course SET visibility = true WHERE visibility = false");
        query.executeUpdate();
    }

    public List<Course> getDataFromPublicCourses() {
        return em.createQuery("SELECT c FROM Course c WHERE c.visibility = true", Course.class)
                .getResultList();
    }

}
