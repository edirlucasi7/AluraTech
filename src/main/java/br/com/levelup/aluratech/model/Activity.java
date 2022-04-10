package br.com.levelup.aluratech.model;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "activity")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z0-9-]*$", message = "O código deve conter apenas letras minúsculas, números e hífen!")
    private String code;
    private boolean active;
    @Min(0)
    @Column(name = "order_visualization")
    private Integer order;
    @NotNull(message = "A seção da qual a atividade faz parte é obrigatória!")
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @Deprecated
    public Activity() {
    }

    public Activity(String title, String code, Section section) {
        cantBeNullOrEmpty(title, "The field title should not be empty!");
        containOnlyLettersLowerCaseAndNumbersAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        cantBeNull(section, "The object section should not be null!");
        this.title = title;
        this.code = code;
        this.section = section;
    }
}
