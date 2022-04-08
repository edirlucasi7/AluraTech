package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O nome não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve conter apenas letras minúsculas e hífen!")
    private String code;
    @Min(0)
    @Column(name = "order_visualization")
    private Integer order;
    private boolean active;
    private boolean test;
    @NotBlank(message = "O curso do qual a seção faz parte é obrigatório!")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Deprecated
    public Section() {
    }

    public Section(String name, String code, Course course) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowerCaseAndNumbersAndDash(code);
        cantBeNull(course, "The object course should not be null!");
        this.name = name;
        this.code = code;
        this.course = course;
    }
}
