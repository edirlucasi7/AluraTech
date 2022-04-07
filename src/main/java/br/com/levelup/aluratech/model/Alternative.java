package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.cantBeNullOrEmpty;

@Entity
@Table(name = "alternative")
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O texto não pode ser vazio!")
    private String text;
    @NotNull(message = "A ordem não pode ser vazia!")
    @Column(name = "order_visualization")
    @Min(0)
    private Integer order;
    @NotNull(message = "A indicação se está correta ou não pode ser vazia!")
    @Column(name = "is_correct")
    private boolean isCorrect;
    private String justification;
    @NotNull(message = "A questão ao qual está associada é obrigatória!")
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @Deprecated
    public Alternative() {
    }

    public Alternative(String text, Boolean isCorrect, Question question) {
        cantBeNullOrEmpty(text, "The field text should not be empty!");
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }
}
