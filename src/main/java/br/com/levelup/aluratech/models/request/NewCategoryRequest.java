package br.com.levelup.aluratech.models.request;

import br.com.levelup.aluratech.models.Category;
import br.com.levelup.aluratech.shared.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NewCategoryRequest {

    @NotBlank
    private final String name;
    @NotBlank
    @Pattern(regexp = "^[a-z-]*$")
    @UniqueValue(domainClass = Category.class, fieldName = "code")
    private final String code;
    private final String shortDescription;
    private final String studyGuide;
    private final boolean active;
    private final Integer order;
    private final String imageUrl;
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
    private final String colorCode;

    public NewCategoryRequest(String name, String code, String shortDescription, String studyGuide, boolean active,
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
        return new Category(name, code, shortDescription, studyGuide, active, order, imageUrl, colorCode);
    }
}
