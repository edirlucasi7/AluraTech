package br.com.levelup.aluratech.controller.validator.category;

import br.com.levelup.aluratech.controller.request.UpdateCategoryRequest;
import br.com.levelup.aluratech.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CheckCategoryUniqueNameForEditionFormValidator implements Validator {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateCategoryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        UpdateCategoryRequest form = (UpdateCategoryRequest) target;
        if(categoryRepository.existsByNameWithDifferentId(form.getName(), form.getId())) {
            errors.rejectValue("name","category.name.exists");
        }
    }
}
