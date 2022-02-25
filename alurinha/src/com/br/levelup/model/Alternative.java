package com.br.levelup.model;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

public class Alternative {

    private String text;
    private Integer order;
    private boolean isCorrect;
    private String justification;
    private Question question;

    public Alternative(String text, Boolean isCorrect, Question question) {
        cantBeNullOrEmpty(text, "The field text should not be empty!");
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "text='" + text + '\'' +
                ", order=" + order +
                ", isCorrect=" + isCorrect +
                ", justification='" + justification + '\'' +
                ", question=" + question +
                '}';
    }

}
