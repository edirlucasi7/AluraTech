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

    public Course(String name, String code, Integer stimatedTime, Instructor instructor) {
        StringValidator.cantBeNotEmpty(name, "The field name should not be empty or null!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be out of format!");
        CourseValidator.isBetween(stimatedTime, "The field stimated time should not be out of time range!");
        this.name = name;
        this.code = code;
        this.stimatedTime = stimatedTime;
        this.instructor = instructor;
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
