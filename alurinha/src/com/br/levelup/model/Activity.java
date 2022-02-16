package com.br.levelup.model;

import validators.StringValidator;

import static validators.IntegerValidator.cantBeLessOrEqualZeroOrNull;
import static validators.ObjectValidator.cantBeNull;

public abstract class Activity {

    private String title;
    private String code;
    private boolean active;
    private Integer order;
    private Section section;

    public Activity(String title, String code, Section section) {
        StringValidator.cantBeNullOrEmpty(title, "The field title should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        cantBeNull(section, "The object section should not be null!");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        cantBeLessOrEqualZeroOrNull(order, "The field order should not be less or equal zero or null!");
        this.order = order;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", indication=" + active +
                ", order=" + order +
                ", section=" + section +
                '}';
    }

}
