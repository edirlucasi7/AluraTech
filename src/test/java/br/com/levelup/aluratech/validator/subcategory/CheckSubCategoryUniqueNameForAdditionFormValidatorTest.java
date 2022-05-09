package br.com.levelup.aluratech.validator.subcategory;

import br.com.levelup.aluratech.controller.request.NewSubCategoryRequest;
import br.com.levelup.aluratech.controller.validator.subcategory.CheckSubCategoryUniqueNameForAdditionFormValidator;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CheckSubCategoryUniqueNameForAdditionFormValidatorTest {

    private SubCategoryRepository subCategoryRepository = mock(SubCategoryRepository.class);
    private CheckSubCategoryUniqueNameForAdditionFormValidator validator = new CheckSubCategoryUniqueNameForAdditionFormValidator(subCategoryRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_name_exists_should_return_error() {
        when(subCategoryRepository.existsByName("Programação")).thenReturn(true);

        NewSubCategoryRequest newSubCategoryRequest = new NewSubCategoryRequest();
        newSubCategoryRequest.setName("Programação");

        validator.validate(newSubCategoryRequest, errors);

        verify(errors).rejectValue("name", "O nome da subcategoria já existe!");
    }

    @Test
    public void when_name_does_not_exists_should_not_return_error() {
        when(subCategoryRepository.existsByName("Programação")).thenReturn(false);

        NewSubCategoryRequest newSubCategoryRequest = new NewSubCategoryRequest();
        newSubCategoryRequest.setName("Programação");

        validator.validate(newSubCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
