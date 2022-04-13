package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import static br.com.levelup.aluratech.model.utils.EstimateValuesUtils.minimumAndMaximumValue;
import static br.com.levelup.aluratech.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "course")
public class Course {

    private static final Integer ESTIMATED_TIME_MIN = 1;
    private static final Integer ESTIMATED_TIME_MAX = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z0-9-]*$", message = "O código deve conter apenas letras minúsculas, números e hífen!")
    private String code;
    @NotNull(message = "A estimativa (em horas) é obrigatória!")
    @Min(1) @Max(20)
    @Column(name = "estimated_time_in_hours", columnDefinition = "TINYINT")
    private Integer estimatedTimeInHours;
    private boolean visibility;
    @Column(name = "target_audience", columnDefinition = "TEXT")
    private String targetAudience;
    @Column(columnDefinition = "TEXT")
    private String resume;
    @Column(name = "developed_skills", columnDefinition = "TEXT")
    private String developedSkills;
    @NotNull(message = "O instrutor deve ser obrigatório!")
    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;
    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private SubCategory subCategory;

    @Deprecated
    public Course() { }

    public Course(String name, String code, Integer estimatedTimeInHours, Instructor instructor, SubCategory subCategory) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowerCaseAndNumbersAndDash(code);
        isBetween(estimatedTimeInHours, "The field stimated time should not be out of time range!");
        cantBeNull(instructor);
        cantBeNull(subCategory, "The object subCategory should not be null!");
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public boolean isVisibility() {
        return visibility;
    }

    private void isBetween(Integer field, String error) {
        if(!minimumAndMaximumValue(field, ESTIMATED_TIME_MIN, ESTIMATED_TIME_MAX)) {
            throw new IllegalArgumentException(error);
        }
    }
}
