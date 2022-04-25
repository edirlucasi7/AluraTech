package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.shared.ExistsId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UpdateSubCategoryRequest {

    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve conter apenas letras minúsculas e hífen!")
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    @Min(0)
    private Integer order;
    @NotNull(message = "A categroia é obrigatória!")
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long idCategory;

    @Deprecated
    public UpdateSubCategoryRequest() {
    }

    public UpdateSubCategoryRequest(SubCategory subCategory) {
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.code = subCategory.getCode();
        this.shortDescription = subCategory.getShortDescription();
        this.studyGuide = subCategory.getStudyGuide();
        this.active = subCategory.isActive();
        this.order = subCategory.getOrder();
        this.idCategory = subCategory.getIdCategory();
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }
}
