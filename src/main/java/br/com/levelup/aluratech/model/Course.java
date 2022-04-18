package br.com.levelup.aluratech.model;

import br.com.levelup.aluratech.controller.request.UpdateCourseRequest;

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
    @NotNull(message = "A subcategoria deve ser obrigatório!")
    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private SubCategory subCategory;

    @Deprecated
    public Course() { }

    public Course(String name, String code, Integer estimatedTimeInHours, String targetAudience, boolean visibility, String resume,
                  String developedSkills, Instructor instructor, SubCategory subCategory) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowerCaseAndNumbersAndDash(code);
        isBetween(estimatedTimeInHours, "The field stimated time should not be out of time range!");
        cantBeNull(instructor);
        cantBeNull(subCategory, "The object subCategory should not be null!");
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.targetAudience = targetAudience;
        this.visibility = visibility;
        this.resume = resume;
        this.developedSkills = developedSkills;
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

    public boolean isVisibility() {
        return visibility;
    }

    public String getCodeCategory() {
        return this.subCategory.getCategoryCode();
    }

    public String getCodeSubCategory() {
        return this.subCategory.getCode();
    }

    public Long getId() {
        return id;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getResume() {
        return resume;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Long getInstructorId() {
        return instructor.getId();
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Long getSubCategoryId() {
        return subCategory.getId();
    }

    private void isBetween(Integer field, String error) {
        if(!minimumAndMaximumValue(field, ESTIMATED_TIME_MIN, ESTIMATED_TIME_MAX)) {
            throw new IllegalArgumentException(error);
        }
    }

    public void update(UpdateCourseRequest updateCourseRequest, Instructor instructor, SubCategory subCategory) {
        cantBeNull(updateCourseRequest);
        cantBeNull(instructor);
        cantBeNull(subCategory);
        this.name = updateCourseRequest.getName();
        this.code = updateCourseRequest.getCode();
        this.estimatedTimeInHours = updateCourseRequest.getEstimatedTimeInHours();
        this.targetAudience = updateCourseRequest.getTargetAudience();
        this.visibility = updateCourseRequest.isVisibility();
        this.resume = updateCourseRequest.getResume();
        this.developedSkills = updateCourseRequest.getDevelopedSkills();
        this.instructor = instructor;
        this.subCategory = subCategory;
    }
}
