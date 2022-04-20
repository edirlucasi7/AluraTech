package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.shared.ExistsId;
import br.com.levelup.aluratech.shared.UniqueValue;

import javax.validation.constraints.*;

public class UpdateCourseRequest {

    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z0-9-]*$", message = "O código deve conter apenas letras minúsculas, números e hífen!")
    @UniqueValue(domainClass = Category.class, fieldName = "code")
    private String code;
    @NotNull(message = "A estimativa (em horas) é obrigatória!")
    @Min(1) @Max(20)
    private Integer estimatedTimeInHours;
    private String targetAudience;
    private boolean visibility;
    private String resume;
    private String developedSkills;
    @NotNull(message = "O instrutor deve ser obrigatório!")
    @ExistsId(domainClass = Instructor.class, fieldName = "id")
    private Long idInstructor;
    @NotNull(message = "A subcategoria deve ser obrigatória!")
    @ExistsId(domainClass = SubCategory.class, fieldName = "id")
    private Long idSubCategory;

    @Deprecated
    public UpdateCourseRequest() {
    }

    public UpdateCourseRequest(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.targetAudience = course.getTargetAudience();
        this.visibility = course.isVisibility();
        this.resume = course.getResume();
        this.developedSkills = course.getDevelopedSkills();
        this.idInstructor = course.getInstructorId();
        this.idSubCategory = course.getSubCategoryId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
