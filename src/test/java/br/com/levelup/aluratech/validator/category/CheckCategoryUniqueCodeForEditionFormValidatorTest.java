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

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();
        updateCategoryRequest.setId(999L);
        updateCategoryRequest.setCode("programacao");

        validator.validate(updateCategoryRequest, errors);

        verify(errors).rejectValue("code", "O código da categoria já existe!");
    }

    @Test
    public void when_code_exists_and_id_is_equal_should_return_error() {
        when(categoryRepository.existsByNameWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest();
        updateCategoryRequest.setId(1L);
        updateCategoryRequest.setName("programacao");

        validator.validate(updateCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
