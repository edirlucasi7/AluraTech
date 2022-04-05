package br.com.levelup.aluratech.models.response;

import br.com.levelup.aluratech.models.Category;

public class NewCategoryResponse {

    private final String name;
    private final String code;
    private final String shortDescription;
    private final boolean active;
    private final String studyGuide;
    private final Integer order;
    private final String imageUrl;
    private final String colorCode;

    public NewCategoryResponse(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.shortDescription = category.getShortDescription();
        this.active = category.isActive();
        this.studyGuide = category.getStudyGuide();
        this.order = category.getOrder();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
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

    public boolean isActive() {
        return active;
    }

    public String getStudyGuide() {
        return studyGuide;
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
}
