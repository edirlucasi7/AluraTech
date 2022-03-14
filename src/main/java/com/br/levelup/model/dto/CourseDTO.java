package com.br.levelup.model.dto;

public class CourseDTO {

    private Long idCourse;
    private String name;
    private Integer estimatedTimeInHours;
    private Long subCategoryId;
    private String subcategoryName;

    public CourseDTO(Long idCourse, String name, Integer estimatedTimeInHours, Long subCategoryId, String subcategoryName) {
        this.idCourse = idCourse;
        this.name = name;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.subCategoryId = subCategoryId;
        this.subcategoryName = subcategoryName;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public String getName() {
        return name;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public Long getIdSubCategory() {
        return subCategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "idCourse=" + idCourse +
                ", name='" + name + '\'' +
                ", estimatedTimeInHours=" + estimatedTimeInHours +
                ", subCategoryId=" + subCategoryId +
                ", subcategoryName='" + subcategoryName + '\'' +
                '}';
    }

}
