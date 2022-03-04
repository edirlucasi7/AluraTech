package com.br.levelup.model;

import static com.br.levelup.model.utils.ValidatorUtils.*;

public abstract class Activity {

    private String title;
    private String code;
    private boolean active;
    private Integer order;
    private Section section;

    public Activity(String title, String code, Section section) {
        cantBeNullOrEmpty(title, "The field title should not be empty!");
        containOnlyLettersLowercaseAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        cantBeNull(section, "The object section should not be null!");
        this.title = title;
        this.code = code;
        this.section = section;
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
