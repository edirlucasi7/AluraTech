package com.br.levelup;

import com.br.levelup.dao.CourseDAO;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class DeleteCourseWithJPA {

    public static void main(String[] args) throws SQLException {

        EntityManager em = JPAUtil.getEntityManager();
        CourseDAO courseDAO = new CourseDAO(em);

        em.getTransaction().begin();
        courseDAO.deleteByCode("java-design-teste");
        em.getTransaction().commit();
        em.close();

    }

}
