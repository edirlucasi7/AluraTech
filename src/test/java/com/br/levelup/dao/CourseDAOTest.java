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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseDAOTest {

    private CourseDAO courseDAO;
    private EntityManager manager;
    private Category category;
    private SubCategory subCategory;
    private Instructor instructor;

    @BeforeEach
    public void beforeEach() {
        this.manager = JPAUtil.getEntityManager("tests");
        this.courseDAO = new CourseDAO(manager);
        manager.getTransaction().begin();
        this.category = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .withActive(true)
                .toEntity();
        manager.persist(category);
        this.subCategory = new SubCategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .withActive(true)
                .withCategory(category)
                .toEntity();
        manager.persist(subCategory);
        this.instructor = new InstructorBuilder()
                .withName("Sergio")
                .toEntity();
        manager.persist(instructor);
    }

    @AfterEach
    public void afterEach() {
        manager.getTransaction().rollback();
    }

    @Test
    void should_retrieve_all_public_courses() {
        Course visibleCourse = new CourseBuilder()
                .withName("Java")
                .withCode("java-iniciante")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor)
                .withSubCategory(subCategory)
                .toEntity();

        Course invisibleCourse = new CourseBuilder()
                .withName("JavaScript")
                .withCode("javascript-iniciante")
                .withEstimatedTimeInHours(12)
                .withInstructor(instructor)
                .withSubCategory(subCategory)
                .toEntity();

        manager.persist(visibleCourse);
        manager.persist(invisibleCourse);

        List<Course> dataFromPublicCourses = courseDAO.getDataFromPublicCourses();

        assertTrue(dataFromPublicCourses.size() == 1);
        assertTrue(dataFromPublicCourses.containsAll(List.of(visibleCourse)));
        assertFalse(dataFromPublicCourses.contains(invisibleCourse));
    }

}
