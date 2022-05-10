package br.com.levelup.aluratech.controller.validator.subcategory;

import br.com.levelup.aluratech.controller.request.NewSubCategoryRequest;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CheckSubCategoryUniqueNameForAdditionFormValidator implements Validator {

    private final SubCategoryRepository subCategoryRepository;

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
            errors.rejectValue("name", "subcategory.name.exists");
        }
    }
}
