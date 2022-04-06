package br.com.levelup.aluratech.model;

import javax.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Deprecated
    public Instructor() {
    }

    public Instructor(String name) {
        this.name = name;
    }
}
