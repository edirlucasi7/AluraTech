package br.com.levelup.aluratech.controller.response.subcategory;

import br.com.levelup.aluratech.model.SubCategory;

public class SubCategoryResponse {

    private String name;
    private String code;
    private String studyGuide;

    public SubCategoryResponse(SubCategory subCategory) {
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.studyGuide = subCategory.getStudyGuide();
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
}
