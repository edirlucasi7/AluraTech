package com.br.levelup.model;

import static validators.IntegerValidator.cantBeLessOrEqualZeroOrNull;
import static validators.StringValidator.cantBeNullOrEmpty;

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

    public void setOrder(Integer order) {
        cantBeLessOrEqualZeroOrNull(order, "The field order should not be less or equal zero or null!");
        this.order = order;
    }

    public void setJustification(String justification) {
        cantBeNullOrEmpty(justification, "The field order should not be less or equal zero or null!");
        this.justification = justification;
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
