package com.br.levelup.model.dto;

import com.br.levelup.model.Course;

import java.util.List;
import java.util.stream.Collectors;

public class PublicCourseDTO {

    private final String name;
    private final Integer estimatedTimeInHours;
    private final Long subCategoryId;
    private final String subCategoryName;

    public PublicCourseDTO(Course course) {
        this.name = course.getName();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.subCategoryId = course.getSubCategoryId();
        this.subCategoryName = course.getSubCategoryName();
    }

    public String getName() {
        return name;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public static List<PublicCourseDTO> convert(List<Course> categories) {
        return categories.stream().map(PublicCourseDTO::new).collect(Collectors.toList());
    }

}
