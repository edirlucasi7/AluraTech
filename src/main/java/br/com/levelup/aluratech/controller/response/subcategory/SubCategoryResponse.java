package br.com.levelup.aluratech.controller.response.subcategory;

import br.com.levelup.aluratech.model.SubCategory;

public class SubCategoryResponse {

    private String name;
    private String code;
    private boolean active;
    private String categoryCode;
    private String studyGuide;

    public SubCategoryResponse(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.studyGuide = subCategory.getStudyGuide();
    }

    public SubCategoryResponse(String name, String code, boolean active, String categoryCode) {
        this.name = name;
        this.code = code;
        this.active = active;
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public String getCategoryCode() {
        return categoryCode;
    }
}
