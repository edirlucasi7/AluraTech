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

        UpdateSubCategoryRequest updateSubCategoryRequest = UpdateSubCategoryRequest
                .builder()
                .id(999L)
                .name("Programação")
                .build();

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors).rejectValue("name", "subcategory.name.exists");
    }

    @Test
    public void when_name_exists_and_id_is_equal_should_not_return_error() {
        when(subCategoryRepository.existsByNameWithDifferentId(eq("Programação"), not(eq(1L)))).thenReturn(true);

        UpdateSubCategoryRequest updateSubCategoryRequest = UpdateSubCategoryRequest
                .builder()
                .id(1L)
                .name("Programação")
                .build();

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_name_not_exists_for_id_not_should_return_error() {
        UpdateSubCategoryRequest updateSubCategoryRequest = UpdateSubCategoryRequest
                .builder()
                .id(1L)
                .name("Business")
                .build();

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_name_not_exists_and_id_is_different_not_should_return_error() {
        UpdateSubCategoryRequest updateSubCategoryRequest = UpdateSubCategoryRequest
                .builder()
                .id(999L)
                .name("Business")
                .build();

        validator.validate(updateSubCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
