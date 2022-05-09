package br.com.levelup.aluratech.validator.subcategory;

import br.com.levelup.aluratech.controller.request.UpdateSubCategoryRequest;
import br.com.levelup.aluratech.controller.validator.subcategory.CheckSubCategoryUniqueNameForEditionFormValidator;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CheckSubCategoryUniqueNameForEditionFormValidatorTest {

    private SubCategoryRepository subCategoryRepository = mock(SubCategoryRepository.class);
    private CheckSubCategoryUniqueNameForEditionFormValidator validator = new CheckSubCategoryUniqueNameForEditionFormValidator(subCategoryRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_name_exists_but_id_is_different_should_return_error() {
        when(subCategoryRepository.existsByNameWithDifferentId(eq("Programação"), not(eq(1L)))).thenReturn(true);

        UpdateSubCategoryRequest updateSubCategoryRequest = new UpdateSubCategoryRequest();
        updateSubCategoryRequest.setId(999L);
        updateSubCategoryRequest.setName("Programação");

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors).rejectValue("name", "O nome da subcategoria já existe!");
    }

    @Test
    public void when_name_exists_and_id_is_equal_not_should_return_error() {
        when(subCategoryRepository.existsByNameWithDifferentId(eq("Programação"), not(eq(1L)))).thenReturn(true);

        UpdateSubCategoryRequest updateSubCategoryRequest = new UpdateSubCategoryRequest();
        updateSubCategoryRequest.setId(1L);
        updateSubCategoryRequest.setName("Programação");

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
