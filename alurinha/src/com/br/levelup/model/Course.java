package com.br.levelup.model;

import static validators.course.CourseValidator.isBetween;
import static validators.ObjectValidator.cantBeNull;
import static validators.StringValidator.*;

public class Course {

    private String name;
    private String code;
    private Integer estimatedTime;
    private boolean visibility;
    private String targetAudience;
    private String instructorName;
    private String resume;
    private String developedSkills;
    private SubCategory subCategory;

    public Course(String name, String code, Integer estimatedTime, Instructor instructor, SubCategory subCategory) {
        cantBeNull(name, "The field name should not be null!");
        containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        isBetween(estimatedTime, "The field stimated time should not be out of time range!");
        cantBeNull(instructor, "The object instructor should not be null!");
        cantBeNull(subCategory, "The object subCategory should not be null!");
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.instructorName = instructor.getName();
        this.subCategory = subCategory;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setTargetAudience(String targetAudience) {
        cantBeNullOrEmpty(targetAudience, "The field targetAudience should not be null or empty!");
        this.targetAudience = targetAudience;
    }

    public void setResume(String resume) {
        cantBeNullOrEmpty(resume, "The field courseResume should not be null or empty!");
        this.resume = resume;
    }

    public void setDevelopedSkills(String developedSkills) {
        cantBeNullOrEmpty(resume, "The field developedSkills should not be null or empty!");
        this.developedSkills = developedSkills;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", stimatedTime=" + estimatedTime +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor=" + instructorName +
                ", resume='" + resume + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }

}
