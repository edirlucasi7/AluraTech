package br.com.levelup.aluratech.controller.validator.course;

import br.com.levelup.aluratech.controller.request.UpdateCourseRequest;
import br.com.levelup.aluratech.repository.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckCourseUniqueCodeForEditionFormValidator implements Validator {

    private CourseRepository courseRepository;

    public CheckCourseUniqueCodeForEditionFormValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateCourseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        UpdateCourseRequest form = (UpdateCourseRequest) target;
        if(courseRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
            errors.rejectValue("code", "O código do curso já existe!");
        }
    }
}
