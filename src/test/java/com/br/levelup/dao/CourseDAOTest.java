package com.br.levelup.dao;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;
import com.br.levelup.model.utils.builders.CategoryBuilder;
import com.br.levelup.model.utils.builders.CourseBuilder;
import com.br.levelup.model.utils.builders.InstructorBuilder;
import com.br.levelup.model.utils.builders.SubCategoryBuilder;
import com.br.levelup.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDAOTest {

    private CourseDAO courseDAO;
    private EntityManager manager;

    @BeforeEach
    public void beforeEach() {
        this.manager = JPAUtil.getEntityManager();
        this.courseDAO = new CourseDAO(manager);
        manager.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        manager.getTransaction().rollback();
    }

    @Test
    void should_retrieve_all_public_courses() {
        Category category = new CategoryBuilder().withName("Programacao").withCode("java-oo")
                .toEntity();
        manager.persist(category);
        category.setActive(true);
        SubCategory activeSubCategory = new SubCategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .withCategory(category)
                .toEntity();
        activeSubCategory.setActive(true);
        manager.persist(activeSubCategory);

        Instructor instructor = new InstructorBuilder()
                .withName("Sergio")
                .toEntity();
        manager.persist(instructor);

        Course activeCourse = new CourseBuilder()
                .withName("Java")
                .withCode("java-iniciante")
                .withEstimatedTimeInHours(12)
                .withInstructor(instructor)
                .withSubCategory(activeSubCategory)
                .toEntity();
        activeCourse.setVisibility(true);

        Course inactiveCourse = new CourseBuilder()
                .withName("JavaScript")
                .withCode("javascript-iniciante")
                .withEstimatedTimeInHours(12)
                .withInstructor(instructor)
                .withSubCategory(activeSubCategory)
                .toEntity();

        manager.persist(activeCourse);
        manager.persist(inactiveCourse);

        List<Course> dataFromPublicCourses = courseDAO.getDataFromPublicCourses();

        Assertions.assertTrue(dataFromPublicCourses.size() == 1);
        Assertions.assertTrue(dataFromPublicCourses.containsAll(List.of(activeCourse)));
        Assertions.assertFalse(dataFromPublicCourses.contains(inactiveCourse));

    }

}
