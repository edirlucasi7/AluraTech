package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "subcategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve conter apenas letras minúsculas e hífen!")
    private String code;
    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;
    @Column(name = "study_guide", columnDefinition = "TEXT")
    private String studyGuide;
    private boolean active;
    @Min(0)
    @Column(name = "order_visualization")
    private Integer order;
    @NotNull(message = "A categoria é obrigatória!")
    @ManyToOne
    private Category category;

    @Deprecated
    public SubCategory() {
    }

    public SubCategory(String name, String code, Category category) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowerCaseAndDash(code);
        cantBeNull(category, "The object category should not be null!");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public Category getCategory() {
        return category;
    }
}
