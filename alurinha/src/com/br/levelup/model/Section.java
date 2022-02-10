package com.br.levelup.model;

import validators.StringValidator;

public class Section {

    private String name;
    private String code;
    private Integer order;
    private Indication indication = Indication.INATIVA;
    private Boolean isTest = false;
    private Course course;

    public Section(String name, String code, Course course) {
        StringValidator.cantBeNotEmpty(name, "The field name should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be out of format!");
        this.name = name;
        this.code = code;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                ", indication=" + indication +
                ", isTest=" + isTest +
                ", course=" + course +
                '}';
    }

}
