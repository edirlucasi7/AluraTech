package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.shared.UniqueValue;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCategoryRequest {

    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve conter apenas letras minúsculas e hífen!")
    @UniqueValue(domainClass = Category.class, fieldName = "code", message = "O código da categoria já existe!")
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    @Min(0)
    private Integer order;
    @URL(message = "A imagem deve ter um formato de URL!")
    private String imageUrl;
    @Size(max = 7)
    private String colorCode;

    public Category toEntity() {
        return new Category(name, code, shortDescription, studyGuide, order, active, imageUrl, colorCode);
    }
}
