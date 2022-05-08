package br.com.levelup.aluratech.controller.validator.subcategory;

import br.com.levelup.aluratech.controller.request.NewSubCategoryRequest;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckSubCategoryUniqueNameForAdditionFormValidator implements Validator {

    private final SubCategoryRepository subCategoryRepository;

    public CheckSubCategoryUniqueNameForAdditionFormValidator(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewSubCategoryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NewSubCategoryRequest form = (NewSubCategoryRequest) target;
        if(subCategoryRepository.existsByName(form.getName())) {
            errors.rejectValue("name", null,"O nome da subcategoria já existe!");
        }
    }
}
