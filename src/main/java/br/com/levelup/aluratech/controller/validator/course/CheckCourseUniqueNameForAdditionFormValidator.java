package br.com.levelup.aluratech.controller.validator.course;

import br.com.levelup.aluratech.controller.request.NewCourseRequest;
import br.com.levelup.aluratech.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CheckCourseUniqueNameForAdditionFormValidator implements Validator {

    private final CourseRepository courseRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCourseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NewCourseRequest form = (NewCourseRequest) target;
        if(courseRepository.existsByName(form.getName())) {
            errors.rejectValue("name", "course.name.exists");
        }
    }
}
