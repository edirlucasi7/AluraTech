package com.br.levelup.model.dto;

public class SubCategoryDTO {

    private final Long subCategoryId;
    private final String subcategoryName;

    public SubCategoryDTO(Long subCategoryId, String subcategoryName) {
        this.subCategoryId = subCategoryId;
        this.subcategoryName = subcategoryName;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "subCategoryId=" + subCategoryId +
                ", subcategoryName='" + subcategoryName + '\'' +
                '}';
    }

}
