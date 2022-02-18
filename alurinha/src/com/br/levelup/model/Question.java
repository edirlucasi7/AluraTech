package com.br.levelup.model;

import com.br.levelup.model.enums.QuestionType;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNull;
import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

public class Question extends Activity {

    private String enunciation;
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    public Question(String title, String code, Section section, String enunciation) {
        super(title, code, section);
        cantBeNullOrEmpty(enunciation, "The field enunciation should not be null or empty!");
        this.enunciation = enunciation;
    }

    public void setType(QuestionType type) {
        cantBeNull(type, "The enum questionType must be a valid enum!");
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "enunciation='" + enunciation + '\'' +
                ", questionType=" + type +
                '}';
    }

}
