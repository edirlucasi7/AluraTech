package br.com.levelup.aluratech.validator.category;

import br.com.levelup.aluratech.controller.request.NewCategoryRequest;
import br.com.levelup.aluratech.controller.validator.category.CheckCategoryUniqueNameForAdditionFormValidator;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

public class CheckCategoryUniqueNameForAdditionFormValidatorTest {

    private CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private CheckCategoryUniqueNameForAdditionFormValidator validator = new CheckCategoryUniqueNameForAdditionFormValidator(categoryRepository);
    private Errors errors = mock(Errors.class);

    @Test
    public void when_name_exists_should_return_error() {
        when(categoryRepository.existsByName("Programação")).thenReturn(true);

        NewCategoryRequest newCategoryRequest = NewCategoryRequest
                .builder()
                .name("Programação")
                .build();

        validator.validate(newCategoryRequest, errors);

        verify(errors).rejectValue("name", "category.name.exists");
    }

    @Test
    public void when_name_does_not_exists_should_not_return_error() {
        when(categoryRepository.existsByName("Programação")).thenReturn(false);

        NewCategoryRequest newCategoryRequest = NewCategoryRequest
                .builder()
                .name("Programação")
                .build();

        validator.validate(newCategoryRequest, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
