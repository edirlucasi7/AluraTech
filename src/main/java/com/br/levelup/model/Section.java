package com.br.levelup.model;

import static com.br.levelup.model.utils.ValidatorUtils.*;

public class Section {

    private String name;
    private String code;
    private Integer order;
    private boolean active;
    private boolean test;
    private Course course;

    public Section(String name, String code, Course course) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be out of format!");
        cantBeNull(course, "The object course should not be null!");
        this.name = name;
        this.code = code;
        this.course = course;
    }

    public void setOrder(Integer order) {
        cantBeLessZero(order, "The field order should not be less or equal zero or null!");
        this.order = order;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                ", indication=" + active +
                ", isTest=" + test +
                ", course=" + course +
                '}';
    }

}
