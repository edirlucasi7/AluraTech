package br.com.levelup.aluratech.validator.category;

import br.com.levelup.aluratech.controller.request.UpdateCategoryRequest;
import br.com.levelup.aluratech.controller.validator.category.CheckCategoryUniqueNameForEditionFormValidator;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class CheckCategoryUniqueNameForEditionFormValidatorTest {

    private CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private CheckCategoryUniqueNameForEditionFormValidator validator = new CheckCategoryUniqueNameForEditionFormValidator(categoryRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_name_exists_but_id_is_different_should_return_error() {
        when(categoryRepository.existsByNameWithDifferentId(eq("Programação"), not(eq(1L)))).thenReturn(true);

        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(999L)
                .name("Programação")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors).rejectValue("name", "category.name.exists");
    }

    @Test
    public void when_name_exists_and_id_is_equal_should_not_return_error() {
        when(categoryRepository.existsByNameWithDifferentId(eq("Programação"), not(eq(1L)))).thenReturn(true);

        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(1L)
                .name("Programação")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_name_not_exists_for_id_not_should_return_error() {
        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(1L)
                .name("Business")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    public void when_name_not_exists_and_id_is_different_not_should_return_error() {
        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest
                .builder()
                .id(999L)
                .name("Business")
                .build();

        validator.validate(updateCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
