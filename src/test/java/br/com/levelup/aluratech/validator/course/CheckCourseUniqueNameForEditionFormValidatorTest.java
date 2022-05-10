package br.com.levelup.aluratech.validator.course;

import br.com.levelup.aluratech.controller.request.UpdateCourseRequest;
import br.com.levelup.aluratech.controller.validator.course.CheckCourseUniqueNameForEditionFormValidator;
import br.com.levelup.aluratech.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CheckCourseUniqueNameForEditionFormValidatorTest {

    private CourseRepository courseRepository = mock(CourseRepository.class);
    private CheckCourseUniqueNameForEditionFormValidator validator = new CheckCourseUniqueNameForEditionFormValidator(courseRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_name_exists_but_id_is_different_should_return_error() {
        when(courseRepository.existsByNameWithDifferentId(eq("Programação"), not(eq(1L)))).thenReturn(true);

        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .id(999L)
                .name("Programação")
                .build();

        validator.validate(updateCourseRequest, errors);

        verify(errors).rejectValue("name", "course.name.exists");
    }

    @Test
    public void when_name_exists_and_id_is_equal_not_should_return_error() {
        when(courseRepository.existsByNameWithDifferentId(eq("Programação"), not(eq(1L)))).thenReturn(true);

        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .id(1L)
                .name("Programação")
                .build();

        validator.validate(updateCourseRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_name_not_exists_for_id_not_should_return_error() {
        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .id(999L)
                .name("Business")
                .build();

        validator.validate(updateCourseRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_name_not_exists_and_id_is_different_not_should_return_error() {
        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .id(999L)
                .name("Business")
                .build();

        validator.validate(updateCourseRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
