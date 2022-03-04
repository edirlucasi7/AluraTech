package com.br.levelup;

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
import static com.br.levelup.model.utils.EstimateValuesUtils.minimumAndMaximumValue;

public class CourseTest {

    private static final Integer ESTIMATED_TIME_MIN = 1;
    private static final Integer ESTIMATED_TIME_MAX = 20;

    private Instructor instructor = new Instructor("Guilherme");
    private Category category = new Category("Programacao", "java-io");
    private SubCategory subCategory = new SubCategory("OO", "heranca", category);

    private List<Course> courses = List.of(new Course("OO", "oo-iniciante", 10, instructor, subCategory),
            new Course("Java-io", "java-io", 15, instructor, subCategory));

    @ParameterizedTest
    @NullAndEmptySource
    void should_return_invalid_name_argument(String name) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Course(name, "java-oo", 10, instructor, subCategory));
    }

    @ParameterizedTest
    @CsvSource({"Java", "java-*", "java1", "java 1"})
    void should_validate_code_invalid_argument(String code) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Course("OO", code, 10, instructor, subCategory));
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
                () -> new Course("OO", "oo-iniciante", 10, instructor, subCategory));
    }

    @ParameterizedTest
    @CsvSource({
            "0, false", "1, true",
            "2, true", "19, true",
            "20, true", "21, false"
    })
    void should_validate_estimated_time_in_hours(Integer estimatedTimeInHours, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, minimumAndMaximumValue(estimatedTimeInHours, ESTIMATED_TIME_MIN,
                ESTIMATED_TIME_MAX));
    }

    @Test
    void should_retrieve_all_private_courses() {
        courses.forEach(c -> c.setVisibility(true));
        boolean existsPrivateCourse = existsPrivate(courses);
        Assertions.assertTrue(existsPrivateCourse);
    }

    @Test
    void should_retrieve_all_instructors_names() {
        Set<Instructor> expectedInstructors = Set.of(new Instructor("Guilherme"));

        Set<Instructor> instructorsNames = instructorsNames(courses);

        Assertions.assertEquals(expectedInstructors, instructorsNames);
    }

    @Test
    void should_retrieve_all_instructors_and_number_of_courses() {
        Map<String, Long> expectedInstructorAndCourses = Map.of("Guilherme", 2l);

        Map<String, Long> instructorsAndCourses = instructorNamesAndCourses(courses);

        Assertions.assertEquals(expectedInstructorAndCourses, instructorsAndCourses);
    }

    @ParameterizedTest
    @CsvSource({
            "PRIVADA, true",
            "PUBLICO, false",
            "OUTRA COISA, false"
    })
    void should_return_correctly_active(String active, boolean expectedActive) {
        Assertions.assertEquals(expectedActive, convertToBoolean(active));
    }

}
