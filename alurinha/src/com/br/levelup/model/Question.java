package com.br.levelup.model;

import com.br.levelup.model.enums.QuestionType;

public class Question implements ActivityType {

    private String enunciation;
    private QuestionType questionType = QuestionType.SingleAnswer;

    public Question(String enunciation) {
        this.enunciation = enunciation;
    }

    @Override
    public String toString() {
        return "Question{" +
                "enunciation='" + enunciation + '\'' +
                ", questionType=" + questionType +
                '}';
    }

}
