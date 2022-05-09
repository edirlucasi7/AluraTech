package br.com.levelup.aluratech.validator.subcategory;

import br.com.levelup.aluratech.controller.request.UpdateSubCategoryRequest;
import br.com.levelup.aluratech.controller.validator.subcategory.CheckSubCategoryUniqueCodeForEditionFormValidator;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CheckSubCategoryUniqueCodeForEditionFormValidatorTest {

    private SubCategoryRepository subCategoryRepository = mock(SubCategoryRepository.class);
    private CheckSubCategoryUniqueCodeForEditionFormValidator validator = new CheckSubCategoryUniqueCodeForEditionFormValidator(subCategoryRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_code_exists_but_id_is_different_should_return_error() {
        when(subCategoryRepository.existsByCodeWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);

        UpdateSubCategoryRequest updateSubCategoryRequest = new UpdateSubCategoryRequest();
        updateSubCategoryRequest.setId(999L);
        updateSubCategoryRequest.setCode("programacao");

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors).rejectValue("code", "O código da subcategoria já existe!");
    }

    @Test
    public void when_code_exists_and_id_is_equal_should_return_error() {
        when(subCategoryRepository.existsByNameWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);

        UpdateSubCategoryRequest updateSubCategoryRequest = new UpdateSubCategoryRequest();
        updateSubCategoryRequest.setId(1L);
        updateSubCategoryRequest.setName("programacao");

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
