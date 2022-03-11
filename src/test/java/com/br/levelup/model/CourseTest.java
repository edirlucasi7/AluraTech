package com.br.levelup.model;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.br.levelup.model.Course.*;

public class CourseTest {

    private Instructor instructorGuilherme = new Instructor("Guilherme");
    private Category category = new Category("Programacao", "java-io");
    private SubCategory subCategory = new SubCategory("OO", "heranca", category);

    @Test
    void should_build_new_course() {
        Course course = new Course("Java", "java", 12, instructorGuilherme, subCategory);
        Assertions.assertNotNull(course);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_return_invalid_name_argument(String name) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Course(name, "java-oo", 10, instructorGuilherme, subCategory));
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "java1", "java 1"})
    void should_validate_code_invalid_argument(String code) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Course("OO", code, 10, instructorGuilherme, subCategory));
    }

    @ParameterizedTest
    @CsvSource({
            "0, false", "21, false"
    })
    void should_throw_exception_when_estimated_time_in_hours_is_invalid(String code) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Category("Java", code));
    }

    @ParameterizedTest
    @NullSource
    void should_validate_instructor_null_argument(Instructor instructor) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Course("OO", "oo-iniciante", 10, instructor, subCategory));
    }

    @ParameterizedTest
    @NullSource
    void should_validate_subcategory_null_argument(SubCategory subCategory) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Course("OO", "oo-iniciante", 10, instructorGuilherme, subCategory));
    }

    @Test
    void should_retrieve_all_private_courses() {
        Course course1 = new Course("OO", "oo-iniciante", 10, instructorGuilherme, subCategory);
        Course course2 = new Course("Java-io", "java-io", 15, instructorGuilherme, subCategory);
        List<Course> courses = List.of(course1, course2);

        course1.setVisibility(false);
        course2.setVisibility(true);

        boolean existsPrivateCourse = existsPrivate(courses);
        Assertions.assertTrue(existsPrivateCourse);
    }

    @Test
    void should_retrieve_all_instructors_names() {
        Instructor instructorThais = new Instructor("Thais");
        List<Course> courses = List.of(new Course("OO", "oo-iniciante", 10, instructorGuilherme, subCategory),
                new Course("Java-io", "java-io", 15, instructorGuilherme, subCategory),
                new Course("Mysql", "mysql-iniciante", 8, instructorThais, subCategory));

        Set<Instructor> expectedInstructors = Set.of(new Instructor("Guilherme"), new Instructor("Thais"));
        Set<Instructor> instructorsNames = instructorsNames(courses);

        Assertions.assertTrue(instructorsNames.contains(instructorThais));
    }

    @Test
    void should_retrieve_all_instructors_and_number_of_courses() {
        Instructor instructorThais = new Instructor("Thais");
        List<Course> courses = List.of(new Course("OO", "oo-iniciante", 10, instructorGuilherme, subCategory),
                new Course("Java-io", "java-io", 15, instructorGuilherme, subCategory),
                new Course("Mysql", "mysql-iniciante", 8, instructorThais, subCategory));
        Map<String, Long> expectedInstructorAndCourses = Map.of("Guilherme", 2l, "Thais", 1l);

        Map<String, Long> instructorsAndCourses = instructorNamesAndCourses(courses);

        Assertions.assertEquals(expectedInstructorAndCourses, instructorsAndCourses);
    }

    @ParameterizedTest
    @CsvSource({
            "PRIVADA, false",
            "PÚBLICA, true",
            "OUTRA COISA, false"
    })
    void should_return_correctly_active(String active, boolean expectedActive) {
        Assertions.assertEquals(expectedActive, convertToBoolean(active));
    }

}
