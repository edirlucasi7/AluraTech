package com.br.levelup.model;

import validators.StringValidator;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);
        StringValidator.cantBeNotEmpty(text, "The field text should not be empty!");
        this.text = text;
    }

}
