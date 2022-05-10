package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Builder
public class UpdateCategoryRequest {

    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve contar apenas letras minúsculas e hífen!")
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    @Min(0)
    private Integer order;
    private String imageUrl;
    private String colorCode;

    public UpdateCategoryRequest(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.shortDescription = getShortDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.order = category.getOrder();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
    }
}
