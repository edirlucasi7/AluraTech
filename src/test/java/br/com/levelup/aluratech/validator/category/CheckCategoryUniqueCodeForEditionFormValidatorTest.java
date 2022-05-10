package br.com.levelup.aluratech.validator.category;

import br.com.levelup.aluratech.controller.request.UpdateCategoryRequest;
import br.com.levelup.aluratech.controller.validator.category.CheckCategoryUniqueCodeForEditionFormValidator;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CheckCategoryUniqueCodeForEditionFormValidatorTest {

    private CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private CheckCategoryUniqueCodeForEditionFormValidator validator = new CheckCategoryUniqueCodeForEditionFormValidator(categoryRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_code_exists_but_id_is_different_should_return_error() {
        when(categoryRepository.existsByCodeWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);

        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(999L)
                .code("programacao")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors).rejectValue("code", "category.code.exists");
    }

    @Test
    public void when_code_exists_and_id_is_equal_should_return_error() {
        when(categoryRepository.existsByNameWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);

        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(1L)
                .name("programacao")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_code_not_exists_for_id_not_should_return_error() {
        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(1L)
                .code("business")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_code_not_exists_and_id_is_different_not_should_return_error() {
        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(999L)
                .code("business")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
