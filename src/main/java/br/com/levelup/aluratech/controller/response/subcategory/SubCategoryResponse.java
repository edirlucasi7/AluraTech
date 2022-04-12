package br.com.levelup.aluratech.controller.response.subcategory;

import br.com.levelup.aluratech.model.SubCategory;

public class SubCategoryResponse {

    private Long id;
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

    public SubCategoryResponse(Long id, String name, String code, boolean active, String categoryCode) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }
}
