package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.shared.ExistsId;
import br.com.levelup.aluratech.shared.UniqueValue;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.cantBeNull;

public class NewSubCategoryRequest {

    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve conter apenas letras minúsculas e hífen!")
    @UniqueValue(domainClass = SubCategory.class, fieldName = "code", message = "O código da subcategoria já existe!")
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    @Min(0)
    private Integer order;
    @NotNull(message = "A categoria é obrigatória!")
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long idCategory;

    @Deprecated
    public NewSubCategoryRequest() {
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

    public SubCategory toEntity(Category category) {
        cantBeNull(category);
        return new SubCategory(name, code, shortDescription, studyGuide, active, category);
    }
}
