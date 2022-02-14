package com.br.levelup.model;

import validators.CourseValidator;
import validators.StringValidator;

public class Course {

    private String name;
    private String code;
    private Integer stimatedTime;
    private Boolean visibility = false;
    private String targetAudience;
    private String instructorName;
    private String resume;
    private String developedSkills;
    private SubCategory subCategory;

    public Course(String name, String code, Integer stimatedTime, Instructor instructor, SubCategory subCategory) {
        StringValidator.cantBeNull(name, "The field should not be null!");
        StringValidator.cantBeNotEmpty(name, "The field should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field should be out of format!");
        CourseValidator.isBetween(stimatedTime, "The field stimated time should not be out of time range!");
        this.name = name;
        this.code = code;
        this.stimatedTime = stimatedTime;
        this.instructorName = instructor.getName();
        this.subCategory = subCategory;
    }

    public Course(String name, String code, Integer stimatedTime, Boolean visibility, String targetAudience,
                  Instructor instructor, String resume, String developedSkills, SubCategory subCategory) {
        this(name, code, stimatedTime, instructor, subCategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.instructorName = instructor.getName();
        this.resume = resume;
        this.developedSkills = developedSkills;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", stimatedTime=" + stimatedTime +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor=" + instructorName +
                ", resume='" + resume + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }

}
