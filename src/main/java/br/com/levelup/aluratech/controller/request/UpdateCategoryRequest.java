package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UpdateCategoryRequest {

    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z-]*$", message = "O código deve contar apenas letras minúsculas e hífen!")
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    @Min(0)
    private Integer order;
    private String imageUrl;
    private String colorCode;

    @Deprecated
    public UpdateCategoryRequest() {
    }

    public UpdateCategoryRequest(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.shortDescription = getShortDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.order = category.getOrder();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrder() {
        return order;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }
}
