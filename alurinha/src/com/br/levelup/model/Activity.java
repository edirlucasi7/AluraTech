package com.br.levelup.model;

import validators.StringValidator;

public abstract class Activity {

    private String title;
    private String code;
    private Boolean active = false;
    private Integer order;
    private Section section;

    public Activity(String title, String code, Section section) {
        StringValidator.cantBeNotEmpty(title, "The field title should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be out of format!");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public Activity(String title, String code, Boolean active, Integer order, Section section) {
        this(title, code, section);
        this.active = active;
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
