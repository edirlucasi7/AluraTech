package br.com.levelup.aluratech.model;

import br.com.levelup.aluratech.controller.request.UpdateSubCategoryRequest;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "subCategory")
    private List<Course> courses = new ArrayList<>();

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

    public SubCategory(String name, String code, String shortDescription, String studyGuide, boolean active, Category category) {
        this(name, code, category);
        this.shortDescription = shortDescription;
        this.studyGuide = studyGuide;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public String getCategoryCode() {
        return category.getCode();
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Integer getOrder() {
        return order;
    }

    public Long getIdCategory() {
        return category.getId();
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void update(UpdateSubCategoryRequest updateSubCategoryRequest, Category category) {
        cantBeNull(updateSubCategoryRequest);
        cantBeNull(category, "The object category should not be null!");
        this.name = updateSubCategoryRequest.getName();
        this.code = updateSubCategoryRequest.getCode();
        this.shortDescription = updateSubCategoryRequest.getShortDescription();
        this.studyGuide = updateSubCategoryRequest.getStudyGuide();
        this.order = updateSubCategoryRequest.getOrder();
        this.active = updateSubCategoryRequest.isActive();
        this.category = category;
    }

    public void disableActive() {
        if (this.active) {
            this.active = false;
        }
    }
}
