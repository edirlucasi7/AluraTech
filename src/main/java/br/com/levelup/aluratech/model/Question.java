package br.com.levelup.aluratech.model;

import br.com.levelup.aluratech.model.enums.QuestionType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.cantBeNullOrEmpty;

@Entity
@Table(name = "question")
public class Question extends Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O enunciado é obrigatório!")
    private String enunciation;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('SINGLE_ANSWER', 'MULTIPLE_ANSWERS', 'TRUE_OR_FALSE')")
    private QuestionType type = QuestionType.SINGLE_ANSWER;

    @Deprecated
    public Question() {
    }

    public Question(String title, String code, Section section, String enunciation) {
        super(title, code, section);
        cantBeNullOrEmpty(enunciation, "The field enunciation should not be null or empty!");
        this.enunciation = enunciation;
    }
}
