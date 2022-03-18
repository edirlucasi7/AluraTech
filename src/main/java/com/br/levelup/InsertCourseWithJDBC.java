package com.br.levelup;

import com.br.levelup.dao.CategoryDAO;
import com.br.levelup.dao.CourseDAOTeste;
import com.br.levelup.dao.InstructorDAO;
import com.br.levelup.dao.SubCategoryDAO;
import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;

public class InsertCourseWithJDBC {

    public static void main(String[] args) {

        Instructor instructor = new Instructor("Thais");
        Category category = new Category("Design", "design");
        SubCategory subCategory = new SubCategory("Java", "javascript", category);
        Course course = new Course("Curso Java para Data Science: primeiros passos", "java-design",
                10, instructor, subCategory);
        course.setVisibility(false);
        course.setTargetAudience("Devs");
        course.setResume("Aprendendo design de código com java");
        course.setDevelopedSkills("várias habilidades");

        EntityManager em = JPAUtil.getEntityManager();

        InstructorDAO instructorDAO = new InstructorDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);
        SubCategoryDAO subCategoryDAO = new SubCategoryDAO(em);
        CourseDAOTeste courseDAOTeste = new CourseDAOTeste(em);

        em.getTransaction().begin();

        instructorDAO.create(instructor);
        categoryDAO.create(category);
        subCategoryDAO.create(subCategory);
        courseDAOTeste.create(course);

        em.getTransaction().commit();
        em.close();

    }

}
