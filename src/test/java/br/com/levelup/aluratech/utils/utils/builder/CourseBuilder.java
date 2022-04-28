package br.com.levelup.aluratech.utils.utils.builder;

import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;

public class CourseBuilder {

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private boolean visibility;
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

    public CourseBuilder withVisibility(boolean visibility) {
        this.visibility = visibility;
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
        Course course = new Course(name, code, estimatedTimeInHours, instructor, subCategory);
        course.setVisibility(visibility);
        return course;
    }

}
