package br.com.levelup.aluratech.model;

import br.com.levelup.aluratech.controller.request.UpdateCategoryRequest;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
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
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "color_code")
    @Size(max = 7)
    private String colorCode;

    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories = new ArrayList<>();

    @Deprecated
    public Category() {
    }

    public Category(String name, String code) {
        cantBeNullOrEmpty(name, "The field name should not be null or empty!");
        cantBeNullOrEmpty(code, "The field code should not be null or empty!");
        containOnlyLettersLowerCaseAndDash(code, "The field code must not be out of lowercase letters and dash format!");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String shortDescription, String studyGuide, Integer order, boolean active,
                    String imageUrl, String colorCode) {
        this(name, code);
        isHexadecimal(colorCode);
        this.shortDescription = shortDescription;
        this.studyGuide = studyGuide;
        this.order = order;
        this.active = active;
        this.imageUrl = imageUrl;
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

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public String getShortDescription() {
        return shortDescription;
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

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void update(UpdateCategoryRequest updateCategoryRequest) {
        cantBeNullOrEmpty(updateCategoryRequest.getName(), "The field name should not be null or empty!");
        cantBeNullOrEmpty(updateCategoryRequest.getCode(), "The field code should not be null or empty!");
        containOnlyLettersLowerCaseAndDash(updateCategoryRequest.getCode(), "The field code must not be out of lowercase letters and dash format!");
        isHexadecimal(updateCategoryRequest.getColorCode(), "The field codeColor should not be out of hexadecimal format!");
        this.name = updateCategoryRequest.getName();
        this.code = updateCategoryRequest.getCode();
        this.shortDescription = updateCategoryRequest.getShortDescription();
        this.studyGuide = updateCategoryRequest.getStudyGuide();
        this.order = updateCategoryRequest.getOrder();
        this.active = updateCategoryRequest.isActive();
        this.imageUrl = updateCategoryRequest.getImageUrl();
        this.colorCode = updateCategoryRequest.getColorCode();
    }

    public void disableActive() {
        if(this.active) {
            this.active = false;
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", imageUrl='" + imageUrl + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
