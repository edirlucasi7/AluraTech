package br.com.levelup.aluratech.model.response;

import br.com.levelup.aluratech.model.SubCategory;

public class SubCategoryListResponse {

    private final String name;
    private final String code;
    private final String studyGuide;

    public SubCategoryListResponse(SubCategory subCategory) {
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
