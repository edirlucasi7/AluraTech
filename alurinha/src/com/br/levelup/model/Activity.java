package com.br.levelup.model;

import validators.StringValidator;

public class Activity {

    private String title;
    private String code;
    private Indication indication = Indication.INATIVA;
    private Integer order;
    private ActivityType type;
    private Section section;

    public Activity(String title, String code, Section section) {
        StringValidator.cantBeNotEmpty(title, "The field title should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be out of format!");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    public void setTipo(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", indication=" + indication +
                ", order=" + order +
                ", tipo=" + type +
                ", section=" + section +
                '}';
    }

}
