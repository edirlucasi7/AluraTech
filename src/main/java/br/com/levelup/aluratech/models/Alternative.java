package br.com.levelup.aluratech.models;

import javax.persistence.*;

@Entity
@Table(name = "alternative")
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Column(name = "order_visualization")
    private Integer order;
    @Column(name = "is_correct")
    private boolean isCorrect;
    private String justification;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @Deprecated
    public Alternative() {
    }

    public Alternative(String text, Boolean isCorrect, Question question) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }
}
