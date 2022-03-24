package com.br.levelup.model;

import com.br.levelup.model.enums.QuestionType;

import javax.persistence.*;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNull;
import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

@Entity
@Table(name = "question")
public class Question extends Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enunciation;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('SINGLE_ANSWER', 'MULTIPLE_ANSWERS', 'TRUE_OR_FALSE')")
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
