package br.com.levelup.aluratech.controller.response.course;

import br.com.levelup.aluratech.model.Course;

public class CourseResponse {

    private final String name;
    private final String code;
    private final Integer estimatedTimeInHours;
    private final String developedSkills;

    public CourseResponse(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.developedSkills = course.getDevelopedSkills();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }
}
