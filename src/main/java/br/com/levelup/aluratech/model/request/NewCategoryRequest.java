package br.com.levelup.aluratech.model.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.shared.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NewCategoryRequest {

    @NotBlank(message = "O nome não pode ser vazio!")
    private final String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve contar apenas letras minúsculas e hífen!")
    @UniqueValue(domainClass = Category.class, fieldName = "code")
    private final String code;
    private final String shortDescription;
    private final String studyGuide;
    private Boolean active;
    private final Integer order;
    private final String imageUrl;
    private String colorCode;

    public NewCategoryRequest(String name, String code, String shortDescription, String studyGuide, Boolean active,
                              Integer order, String imageUrl, String colorCode) {
        this.name = name;
        this.code = code;
        this.shortDescription = shortDescription;
        this.studyGuide = studyGuide;
        this.active = active;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrder() {
        return order;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public Category toEntity() {
        Category category = new Category(name, code, shortDescription, studyGuide, order, imageUrl, colorCode);
        if(this.active == null) {
            category.setActive(false);
        }
        return category;
    }
}
