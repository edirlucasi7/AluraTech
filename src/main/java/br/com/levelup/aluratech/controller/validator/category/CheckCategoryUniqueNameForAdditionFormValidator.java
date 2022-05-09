package br.com.levelup.aluratech.controller.validator.category;

import br.com.levelup.aluratech.controller.request.NewCategoryRequest;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckCategoryUniqueNameForAdditionFormValidator implements Validator {

    private CategoryRepository categoryRepository;

    public CheckCategoryUniqueNameForAdditionFormValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCategoryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NewCategoryRequest form = (NewCategoryRequest) target;
        if(categoryRepository.existsByName(form.getName())) {
            errors.rejectValue("name", "O nome da categoria já existe!");
        }
    }
}
