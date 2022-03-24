package com.br.levelup;

import com.br.levelup.dao.CourseDAO;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;

public class UpdateCourseWithJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager("alurinha");
        CourseDAO courseDAO = new CourseDAO(em);

        em.getTransaction().begin();
        courseDAO.updateAllPublicVisibility();
        em.getTransaction().commit();
        em.close();
    }

}
