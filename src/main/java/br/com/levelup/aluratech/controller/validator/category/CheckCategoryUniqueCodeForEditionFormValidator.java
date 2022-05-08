package br.com.levelup.aluratech.controller.validator.category;

import br.com.levelup.aluratech.controller.request.UpdateCategoryRequest;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckCategoryUniqueCodeForEditionFormValidator implements Validator {

    private CategoryRepository categoryRepository;

    public CheckCategoryUniqueCodeForEditionFormValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

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
        if(categoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
            errors.rejectValue("code", null,"O nome da categoria já existe!");
        }
    }
}
