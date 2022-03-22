package com.br.levelup.model.utils.builders;

import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;

public class CourseBuilder {

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private Instructor instructor;
    private SubCategory subCategory;

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder withEstimatedTimeInHours(int estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
        return this;
    }

    public CourseBuilder withInstructor(Instructor instructor) {
        this.instructor = instructor;
        return this;
    }

    public CourseBuilder withSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public Course toEntity() {
        return new Course(name, code, estimatedTimeInHours, instructor, subCategory);
    }

}
