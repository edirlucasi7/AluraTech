package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.report.ReportInstructorWithMoreCoursesProjection;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.utils.builder.InstructorBuilder;
import br.com.levelup.aluratech.utils.builder.CategoryBuilder;
import br.com.levelup.aluratech.utils.builder.CourseBuilder;
import br.com.levelup.aluratech.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private EntityManager manager;

    private Category category;
    private SubCategory subCategory;

    @BeforeEach
    public void beforeEach() {
        this.category = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java")
                .withOrder(1)
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
    }

    @Test
    void should_retrieve_the_instructor_with_more_courses() {
        Instructor instructor1 = new InstructorBuilder()
                .withName("Madu")
                .toEntity();

        Instructor instructor2 = new InstructorBuilder()
                .withName("Thais")
                .toEntity();

        Course visibleCourse1 = new CourseBuilder()
                .withName("Java")
                .withCode("java-iniciante")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor1)
                .withSubCategory(subCategory)
                .toEntity();

        Course visibleCourse2 = new CourseBuilder()
                .withName("Java")
                .withCode("java-intermediario")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor2)
                .withSubCategory(subCategory)
                .toEntity();

        Course invisibleCourse = new CourseBuilder()
                .withName("JavaScript")
                .withCode("javascript-iniciante")
                .withEstimatedTimeInHours(7)
                .withInstructor(instructor1)
                .withSubCategory(subCategory)
                .toEntity();

        manager.persist(instructor1);
        manager.persist(instructor2);
        manager.persist(visibleCourse1);
        manager.persist(visibleCourse2);
        manager.persist(invisibleCourse);

        Optional<ReportInstructorWithMoreCoursesProjection> instructorWithMoreCourses = instructorRepository.findInstructorWithMoreCourses();
        assertThat(instructorWithMoreCourses.get()).extracting("name").contains("Madu");
        assertThat(instructorWithMoreCourses.get()).extracting("amount").contains(2);
        assertThat(instructorWithMoreCourses.get()).extracting("name").doesNotContain("Thais");
    }
}
