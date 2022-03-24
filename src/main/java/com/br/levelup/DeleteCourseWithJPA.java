package com.br.levelup;

import com.br.levelup.dao.CourseDAO;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class DeleteCourseWithJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager("alurinha");
        CourseDAO courseDAO = new CourseDAO(em);

        em.getTransaction().begin();
        courseDAO.deleteByCode("java-design-teste");
        em.getTransaction().commit();
        em.close();

    }

}
