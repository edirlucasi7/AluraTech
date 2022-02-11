package com.br.levelup.model;

import com.br.levelup.model.enums.QuestionType;

public class Question extends Activity {

    private String enunciation;
    private QuestionType questionType = QuestionType.SingleAnswer;

    public Question(String title, String code, Section section, String enunciation, QuestionType questionType) {
        super(title, code, section);
        this.enunciation = enunciation;
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "enunciation='" + enunciation + '\'' +
                ", questionType=" + questionType +
                '}';
    }

}
