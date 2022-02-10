package com.br.levelup.model;

import validators.StringValidator;

public class Alternative {

    private String text;
    private Integer order;
    private Boolean isCorrect;
    private String justification;
    private Question question;

    public Alternative(String text, Boolean isCorrect, Question question) {
        StringValidator.cantBeNotEmpty(text, "The field text should not be empty!");
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

}
