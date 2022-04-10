package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.cantBeNullOrEmpty;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O node não pode ser vazio!")
    private String name;

    @Deprecated
    public Instructor() {
    }

    public Instructor(String name) {
        cantBeNullOrEmpty(name, "The field name should not be null or empty!");
        this.name = name;
    }
}
