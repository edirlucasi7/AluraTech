package br.com.levelup.aluratech.repository.projection;

import br.com.levelup.aluratech.controller.projection.category.CategoryWithSubCategoriesAndCoursesProjection;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.utils.builder.CategoryBuilder;
import br.com.levelup.aluratech.utils.builder.CourseBuilder;
import br.com.levelup.aluratech.utils.builder.InstructorBuilder;
import br.com.levelup.aluratech.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

public class CategoryWithSubCategoriesAndCoursesProjectionTest {

    private Category category;

    private Instructor instructor;

    private SubCategory activeSubCategory1;

    private SubCategory activeSubCategory2;

    private Course visibleCourse1;

    private Course visibleCourse2;

    @BeforeEach
    public void beforeEach() {
        this.category = new CategoryBuilder()
                .withName("Programacao")
                .withCode("programacao")
                .withActive(true)
                .toEntity();

        this.instructor = new InstructorBuilder()
                .withName("Thais")
                .toEntity();

        this.activeSubCategory1 = new SubCategoryBuilder()
                .withName("Programacao1")
                .withCode("java-oo")
                .withOrder(2)
                .withActive(true)
                .withCategory(category)
                .toEntity();

        this.activeSubCategory2 = new SubCategoryBuilder()
                .withName("Programacao2")
                .withCode("programacao-java")
                .withOrder(1)
                .withActive(true)
                .withCategory(category)
                .toEntity();

        this.visibleCourse1 = new CourseBuilder()
                .withName("Java Inicante")
                .withCode("java-iniciante")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor)
                .withSubCategory(activeSubCategory1)
                .toEntity();

        this.visibleCourse2 = new CourseBuilder()
                .withName("Java Intermediario")
                .withCode("java-intermediario")
                .withEstimatedTimeInHours(12)
                .withVisibility(true)
                .withInstructor(instructor)
                .withSubCategory(activeSubCategory2)
                .toEntity();
    }

    class CategoryWithSubCategoriesAndCoursesProjectionImpl implements CategoryWithSubCategoriesAndCoursesProjection {

        @Override
        public String getName() {
            return "Teste";
        }

        @Override
        public String getCode() {
            return "teste";
        }

        @Override
        public String getImageUrl() {
            return "http://teste";
        }

        @Override
        public List<SubCategory> getSubCategories() {
            SubCategory activeSubCategory3 = new SubCategoryBuilder()
                    .withName("Desenvolvimento")
                    .withCode("desenvolvimento")
                    .withActive(true)
                    .withCategory(category)
                    .toEntity();

            SubCategory inactiveSubcategory = new SubCategoryBuilder()
                    .withName("PHP")
                    .withCode("php")
                    .withCategory(category)
                    .toEntity();

            activeSubCategory1.getCourses().add(visibleCourse1);
            activeSubCategory2.getCourses().add(visibleCourse2);
            inactiveSubcategory.getCourses().add(visibleCourse1);

            Course invisibleCourse = new CourseBuilder()
                    .withName("JavaScript")
                    .withCode("javascript-iniciante")
                    .withEstimatedTimeInHours(7)
                    .withInstructor(instructor)
                    .withSubCategory(activeSubCategory3)
                    .toEntity();
            activeSubCategory3.getCourses().add(invisibleCourse);

            return List.of(activeSubCategory1, activeSubCategory2, activeSubCategory3, inactiveSubcategory);
        }
    }

    @Test
    void should_retrieve_all_active_subcategories_and_courses_with_at_least_one_visible_course() {
        List<SubCategory> activeSubCategoriesWithCourses = new CategoryWithSubCategoriesAndCoursesProjectionImpl().getActiveSubCategoriesWithVisibleCourses();

        assertThat(activeSubCategoriesWithCourses)
                .extracting("name", "code", "courses")
                .containsExactly(tuple("Programacao1", "java-oo", List.of(visibleCourse1)),
                        tuple("Programacao2", "programacao-java", List.of(visibleCourse2)))
                .doesNotContain(tuple("PHP", "php"),
                        tuple("Desenvolvimento", "desenvolvimento"))
                .hasSize(2);
    }
}
