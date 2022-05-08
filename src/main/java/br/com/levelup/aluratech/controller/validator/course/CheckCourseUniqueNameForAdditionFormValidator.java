package br.com.levelup.aluratech.controller.validator.course;

import br.com.levelup.aluratech.controller.request.NewCourseRequest;
import br.com.levelup.aluratech.repository.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckCourseUniqueNameForAdditionFormValidator implements Validator {

    private CourseRepository courseRepository;

    public CheckCourseUniqueNameForAdditionFormValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

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
            errors.rejectValue("name", null,"O nome da curso já existe!");
        }
    }
}
