package br.com.levelup.aluratech.validator.course;

import br.com.levelup.aluratech.controller.request.NewCourseRequest;
import br.com.levelup.aluratech.controller.validator.course.CheckCourseUniqueNameForAdditionFormValidator;
import br.com.levelup.aluratech.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CheckCourseUniqueNameForAdditionFormValidatorTest {

    private CourseRepository courseRepository = mock(CourseRepository.class);
    private CheckCourseUniqueNameForAdditionFormValidator validator = new CheckCourseUniqueNameForAdditionFormValidator(courseRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_name_exists_should_return_error() {
        when(courseRepository.existsByName("Programação")).thenReturn(true);

        NewCourseRequest newCourseRequest = new NewCourseRequest();
        newCourseRequest.setName("Programação");

        validator.validate(newCourseRequest, errors);

        verify(errors).rejectValue("name", "O nome do curso já existe!");
    }

    @Test
    public void when_name_does_not_exists_should_not_return_error() {
        when(courseRepository.existsByName("Programação")).thenReturn(false);

        NewCourseRequest newCourseRequest = new NewCourseRequest();
        newCourseRequest.setName("Programação");

        validator.validate(newCourseRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
