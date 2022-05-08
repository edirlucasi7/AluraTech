package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.shared.UniqueValue;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewCategoryRequest {

    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve conter apenas letras minúsculas e hífen!")
    @UniqueValue(domainClass = Category.class, fieldName = "code", message = "O código da categoria já existe!")
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    @Min(0)
    private Integer order;
    @URL(message = "A imagem deve ter um formato de URL!")
    private String imageUrl;
    @Size(max = 7)
    private String colorCode;

    @Deprecated
    public NewCategoryRequest(){
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Category toEntity() {
        return new Category(name, code, shortDescription, studyGuide, order, active, imageUrl, colorCode);
    }
}
