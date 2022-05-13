package br.com.levelup.aluratech.controller.validator.subcategory;

import br.com.levelup.aluratech.controller.request.UpdateSubCategoryRequest;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CheckSubCategoryUniqueNameForEditionFormValidator implements Validator {

    private final SubCategoryRepository subCategoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateSubCategoryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        UpdateSubCategoryRequest form = (UpdateSubCategoryRequest) target;
        if(subCategoryRepository.existsByNameWithDifferentId(form.getName(), form.getId())) {
            errors.rejectValue("name", "subcategory.name.exists");
        }
    }
}
