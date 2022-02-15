package com.br.levelup.model;

import validators.StringValidator;

public class Section {

    private String name;
    private String code;
    private Integer order;
    private Boolean active = false;
    private Boolean isTest = false;
    private Course course;

    public Section(String name, String code, Course course) {
        StringValidator.cantBeNotEmpty(name, "The field name should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be out of format!");
        this.name = name;
        this.code = code;
        this.course = course;
    }

    public Section(String name, String code, Integer order, Boolean active, Boolean isTest, Course course) {
        this(name, code, course);
        this.order = order;
        this.active = active;
        this.isTest = isTest;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                ", indication=" + active +
                ", isTest=" + isTest +
                ", course=" + course +
                '}';
    }

}
