package com.br.levelup.model.dto;

public class CourseDTO {

    private final Long idCourse;
    private final String name;
    private final Integer estimatedTimeInHours;
    private final SubCategoryDTO subCategoryDTO;


    public CourseDTO(Long idCourse, String name, Integer estimatedTimeInHours, SubCategoryDTO subCategoryDTO) {
        this.idCourse = idCourse;
        this.name = name;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.subCategoryDTO = subCategoryDTO;
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

    public SubCategoryDTO getSubCategoryDTO() {
        return subCategoryDTO;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "idCourse=" + idCourse +
                ", name='" + name + '\'' +
                ", estimatedTimeInHours=" + estimatedTimeInHours +
                ", subCategoryDTO=" + subCategoryDTO +
                '}';
    }
}
