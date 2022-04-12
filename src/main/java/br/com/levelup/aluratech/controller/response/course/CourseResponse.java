package br.com.levelup.aluratech.controller.response.course;

import br.com.levelup.aluratech.model.Course;

public class CourseResponse {

    private String name;
    private String code;
    private boolean visibility;
    private String developedSkills;
    private Integer estimatedTimeInHours;
    private String categoryCode;
    private String subcategoryCode;

    public CourseResponse(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.developedSkills = course.getDevelopedSkills();
    }

    public CourseResponse(String name, String code, boolean visibility, String categoryCode, String subcategoryCode) {
        this.name = name;
        this.code = code;
        this.visibility = visibility;
        this.categoryCode = categoryCode;
        this.subcategoryCode = subcategoryCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public String getSubcategoryCode() {
        return subcategoryCode;
    }
}
