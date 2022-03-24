package com.br.levelup.model;

import javax.persistence.*;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

@Entity
@Table(name = "explanation")
public class Explanation extends Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);
        cantBeNullOrEmpty(text, "The field text should not be null or empty!");
        this.text = text;
    }

    @Override
    public String toString() {
        return "Explanation{" +
                "text='" + text + '\'' +
                '}';
    }

}
