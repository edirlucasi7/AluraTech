package com.br.levelup.model;

import validators.StringValidator;

public class Explanation implements ActivityType {

    private String text;

    public Explanation(String text) {
        StringValidator.cantBeNotEmpty(text, "The field text should not be empty!");
        this.text = text;
    }

}
