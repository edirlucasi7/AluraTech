package com.br.levelup.model;

import validators.CourseValidator;
import validators.StringValidator;

public class Course {

    private String name;
    private String code;
    private Integer stimatedTime;
    private Boolean visibility = false;
    private String targetAudience;
    private Instructor instructor;
    private String resume;
    private String developedSkills;
    private SubCategory subCategory;

    public Course(String name, String code, Integer stimatedTime, Instructor instructor, SubCategory subCategory) {
        this.name = name;
        this.code = code;
        this.stimatedTime = stimatedTime;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", stimatedTime=" + stimatedTime +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor=" + instructor +
                ", resume='" + resume + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                '}';
    }

}
