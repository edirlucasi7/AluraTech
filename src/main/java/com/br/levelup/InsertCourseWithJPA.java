package com.br.levelup;

import com.br.levelup.dao.*;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;

public class InsertCourseWithJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        InstructorDAO instructorDAO = new InstructorDAO(em);
        SubCategoryDAO subCategoryDAO = new SubCategoryDAO(em);
        CourseDAO courseDAO = new CourseDAO(em);

        Instructor instructor = instructorDAO.findByName("Paulo Silveira");
        SubCategory subCategory = subCategoryDAO.findByCode("java");

        Course course = new Course("Curso Java para Data Science: primeiros passos", "java-design-teste",
                10, instructor, subCategory);
        course.setVisibility(false);
        course.setTargetAudience("Devs");
        course.setResume("Aprendendo design de código com java");
        course.setDevelopedSkills("várias habilidades");

        em.getTransaction().begin();
        courseDAO.create(course);
        em.getTransaction().commit();
        em.close();

    }

}
