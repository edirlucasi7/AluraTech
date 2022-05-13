package br.com.levelup.aluratech.controller.validator.category;

import br.com.levelup.aluratech.controller.request.NewCategoryRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class CheckNewCategoryInvalidColorCodeValidator implements Validator {

    private static final Pattern CODE_FORMAT_HEXADECIMAL = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCategoryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }
        NewCategoryRequest request = (NewCategoryRequest) target;
        if(!request.getColorCode().isEmpty() && !CODE_FORMAT_HEXADECIMAL.matcher(request.getColorCode()).matches()) {
            errors.rejectValue("colorCode", null, "O código da cor deve ser no formato hexadecimal!");
        }
    }
}
