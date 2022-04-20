package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.shared.ExistsId;
import br.com.levelup.aluratech.shared.UniqueValue;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class NewCourseRequest {

    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z0-9-]*$", message = "O código deve conter apenas letras minúsculas, números e hífen!")
    @UniqueValue(domainClass = Category.class, fieldName = "code")
    private String code;
    @NotNull(message = "A estimativa (em horas) é obrigatória!")
    @Min(1) @Max(20)
    @Column(name = "estimated_time_in_hours", columnDefinition = "TINYINT")
    private Integer estimatedTimeInHours;
    private String targetAudience;
    private boolean visibility;
    private String resume;
    private String developedSkills;
    @NotNull(message = "O instrutor deve ser obrigatório!")
    @ExistsId(domainClass = Instructor.class, fieldName = "id")
    private Long idInstructor;
    @ExistsId(domainClass = SubCategory.class, fieldName = "id")
    private Long idSubCategory;

    @Deprecated
    public NewCourseRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public void setEstimatedTimeInHours(Integer estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public void setDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
    }

    public Long getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Long idInstructor) {
        this.idInstructor = idInstructor;
    }

    public Long getIdSubCategory() {
        return idSubCategory;
    }

    public void setIdSubCategory(Long idSubCategory) {
        this.idSubCategory = idSubCategory;
    }

    public Course toEntity(Instructor instructor, SubCategory subCategory) {
        return new Course(name, code, estimatedTimeInHours, targetAudience, visibility, resume, developedSkills, instructor, subCategory);
    }
}
