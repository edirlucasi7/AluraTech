package br.com.levelup.aluratech.model;

import com.br.levelup.model.enums.QuestionType;

import javax.persistence.*;

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

    @Deprecated
    public Question() {
    }

    public Question(String title, String code, Section section, String enunciation) {
        super(title, code, section);
        this.enunciation = enunciation;
    }
}
