package br.com.levelup.aluratech.model;

import javax.persistence.*;

@Entity
@Table(name = "explanation")
public class Explanation extends Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @Deprecated
    public Explanation() {
    }

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);
        this.text = text;
    }
}
