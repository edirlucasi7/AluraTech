package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.shared.ExistsId;
import br.com.levelup.aluratech.shared.UniqueValue;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.cantBeNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewSubCategoryRequest {

    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve conter apenas letras minúsculas e hífen!")
    @UniqueValue(domainClass = SubCategory.class, fieldName = "code", message = "O código da subcategoria já existe!")
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    @Min(0)
    private Integer order;
    @NotNull(message = "A categoria é obrigatória!")
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long idCategory;

    public SubCategory toEntity(Category category) {
        cantBeNull(category);
        return new SubCategory(name, code, shortDescription, studyGuide, active, category);
    }
}
