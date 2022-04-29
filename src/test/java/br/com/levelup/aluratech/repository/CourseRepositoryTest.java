package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.course.CourseProjection;
import br.com.levelup.aluratech.controller.projection.report.ReportOfCoursesByCategoryProjection;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EntityManager manager;

    private Category category;
    private SubCategory subCategory1;

    private SubCategory subCategory2;

    @BeforeEach
    public void beforeEach() {
        this.category = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java")
                .withOrder(1)
                .withActive(true)
                .toEntity();
        manager.persist(category);

        this.subCategory1 = new SubCategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .withActive(true)
                .withCategory(category)
                .toEntity();
        manager.persist(subCategory1);

        this.subCategory2 = new SubCategoryBuilder()
                .withName("PHP")
                .withCode("php")
                .withActive(true)
                .withCategory(category)
                .toEntity();
        manager.persist(subCategory2);
    }

    @Test
    void should_retrieve_all_courses_by_category_sorted_by_order_desc() {
        Category inactiveCategory = new CategoryBuilder()
                .withName("Devops")
                .withCode("docker")
                .toEntity();

        Instructor instructor = new InstructorBuilder()
                .withName("Sergio")
                .toEntity();

        Course visibleCourse = new CourseBuilder()
                .withName("Java")
                .withCode("java-iniciante")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor)
                .withSubCategory(subCategory1)
                .toEntity();

        Course invisibleCourse = new CourseBuilder()
                .withName("JavaScript")
                .withCode("javascript-iniciante")
                .withEstimatedTimeInHours(12)
                .withInstructor(instructor)
                .withSubCategory(subCategory1)
                .toEntity();

        manager.persist(category);
        manager.persist(inactiveCategory);
        manager.persist(subCategory1);
        manager.persist(instructor);
        manager.persist(visibleCourse);
        manager.persist(invisibleCourse);

        List<ReportOfCoursesByCategoryProjection> coursesByCategory = courseRepository.findAllCoursesByCategory();

        assertThat(coursesByCategory)
                .extracting("name", "amount")
                .containsExactly(tuple("Programacao", 2),
                        tuple("Devops", 0))
                .hasSize(2);
    }

    @Test
    void should_retrieve_all_courses_by_subcategory() {
        Instructor instructor = new InstructorBuilder()
                .withName("Thais")
                .toEntity();

        Course visibleCourse1 = new CourseBuilder()
                .withName("Java")
                .withCode("java-iniciante")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor)
                .withSubCategory(subCategory1)
                .toEntity();

        Course visibleCourse2 = new CourseBuilder()
                .withName("Java")
                .withCode("java-intermediario")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor)
                .withSubCategory(subCategory2)
                .toEntity();

        Course invisibleCourse = new CourseBuilder()
                .withName("JavaScript")
                .withCode("javascript-iniciante")
                .withEstimatedTimeInHours(7)
                .withInstructor(instructor)
                .withSubCategory(subCategory1)
                .toEntity();

        manager.persist(instructor);
        manager.persist(visibleCourse1);
        manager.persist(visibleCourse2);
        manager.persist(invisibleCourse);

        Page<CourseProjection> allBySubCategory = courseRepository.getAllBySubCategory("java-oo", Pageable.unpaged());
        assertThat(allBySubCategory.getContent())
                .extracting("name", "code")
                .containsExactly(tuple("Java", "java-iniciante"),
                        tuple("JavaScript", "javascript-iniciante"))
                .hasSize(2);
        assertThat(allBySubCategory.getContent()).extracting("code").doesNotContain("java-intermediario");
    }

}
