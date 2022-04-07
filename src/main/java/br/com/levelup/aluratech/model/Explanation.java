package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.cantBeNullOrEmpty;

@Entity
@Table(name = "explanation")
public class Explanation extends Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O texto não pode ser vazio!")
    private String text;

    @Deprecated
    public Explanation() {
    }

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);
        cantBeNullOrEmpty(text, "The field text should not be null or empty!");
        this.text = text;
    }
}
