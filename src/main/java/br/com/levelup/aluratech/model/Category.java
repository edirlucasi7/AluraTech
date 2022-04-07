package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
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

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
