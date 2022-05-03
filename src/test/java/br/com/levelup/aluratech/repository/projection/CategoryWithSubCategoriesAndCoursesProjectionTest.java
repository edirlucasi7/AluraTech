package br.com.levelup.aluratech.repository.projection;

import br.com.levelup.aluratech.controller.projection.category.CategoryWithSubCategoriesAndCoursesProjection;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.utils.builder.InstructorBuilder;
import br.com.levelup.aluratech.utils.builder.CategoryBuilder;
import br.com.levelup.aluratech.utils.builder.CourseBuilder;
import br.com.levelup.aluratech.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

public class CategoryWithSubCategoriesAndCoursesProjectionTest {

    private Category category = new CategoryBuilder()
            .withName("Programacao")
            .withCode("programacao")
            .withActive(true)
            .toEntity();

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
            SubCategory activeSubCategory1 = new SubCategoryBuilder()
                    .withName("Programacao1")
                    .withCode("java-oo")
                    .withOrder(2)
                    .withActive(true)
                    .withCategory(category)
                    .toEntity();

            SubCategory activeSubCategory2 = new SubCategoryBuilder()
                    .withName("Programacao2")
                    .withCode("programacao-java")
                    .withOrder(1)
                    .withActive(true)
                    .withCategory(category)
                    .toEntity();

            SubCategory activeSubCategory3 = new SubCategoryBuilder()
                    .withName("Desenvolvimento")
                    .withCode("desenvolvimento")
                    .withActive(true)
                    .withCategory(category)
                    .toEntity();

            SubCategory inactiveSubcategory = new SubCategoryBuilder()
                    .withName("PHP")
                    .withCode("php")
                    .withActive(true)
                    .withCategory(category)
                    .toEntity();

            Instructor instructor = new InstructorBuilder()
                    .withName("Thais")
                    .toEntity();

            Course visibleCourse1 = new CourseBuilder()
                    .withName("Java Inicante")
                    .withCode("java-iniciante")
                    .withEstimatedTimeInHours(12)
                    .withVisibility(true)
                    .withInstructor(instructor)
                    .withSubCategory(activeSubCategory1)
                    .toEntity();
            activeSubCategory1.getCourses().add(visibleCourse1);

            Course visibleCourse2 = new CourseBuilder()
                    .withName("Java Intermediario")
                    .withCode("java-intermediario")
                    .withEstimatedTimeInHours(12)
                    .withVisibility(true)
                    .withInstructor(instructor)
                    .withSubCategory(activeSubCategory2)
                    .toEntity();
            activeSubCategory2.getCourses().add(visibleCourse2);

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
    void should_retrieve_all_active_subcategories_and_courses_with_at_least_one_public_course() {
        List<SubCategory> activeSubCategoriesWithCourses = new CategoryWithSubCategoriesAndCoursesProjectionImpl().getSubCategoriesWithCourses();

        assertThat(activeSubCategoriesWithCourses)
                .extracting("name", "code", "courses")
                .containsExactly(tuple("Programacao1", "java-oo", activeSubCategoriesWithCourses.get(0).getCourses()),
                        tuple("Programacao2", "programacao-java", activeSubCategoriesWithCourses.get(1).getCourses()))
                .hasSize(2);
        assertThat(activeSubCategoriesWithCourses).extracting("code").doesNotContain("php");
    }
}
