package com.br.levelup.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static com.br.levelup.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;
    @Column(name = "study_guide", columnDefinition = "TEXT")
    private String studyGuide;
    private boolean active;
    @Column(name = "order_visualization")
    private Integer order;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "color_code")
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

    public Category(String name, String code, String colorCode) {
        this(name, code);
        isHexadecimal(colorCode);
        this.colorCode = colorCode;
    }

    public Category(String name, String code, int order, String shortDescription, boolean active, String imageUrl, String colorCode) {
        this(name, code, colorCode);
        this.shortDescription = shortDescription;
        this.order = order;
        this.active = active;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public Integer getOrder() {
        return order;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setShortDescription(String shortDescription) {
        cantBeNullOrEmpty(shortDescription, "The field shortDescription should not be null or empty!");
        this.shortDescription = shortDescription;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        cantBeLessZero(order, "The field order should not be less than zero!");
        this.order = order;
    }

    public void setImageUrl(String imageUrl) {
        cantBeNullOrEmpty(imageUrl, "The field imageUrl should not be empty!");
        this.imageUrl = imageUrl;
    }

    public void setColorCode(String colorCode) {
        isHexadecimal(colorCode, "The field codeColor should not be out of hexadecimal format!");
        this.colorCode = colorCode;
    }

    public void setStudyGuide(String studyGuide) {
        cantBeNullOrEmpty(studyGuide, "The field studyGuide should not be empty!");
        this.studyGuide = studyGuide;
    }

    public static boolean convertToBoolean(String stringActive) {
        return "ATIVA".equals(stringActive);
    }

    public static Integer processingOrder(String stringOrder) {
        return "".equals(stringOrder) ? 0 : Integer.parseInt(stringOrder);
    }

    public static List<Category> activeCategories(List<Category> categories) {
        return categories.stream().filter(Category::isActive).toList();
    }

    public void update(String name, String code, String shortDescription, String studyGuide, boolean active,
                       Integer order, String imageUrl, String colorCode) {
        cantBeNullOrEmpty(name, "The field name should not be null or empty!");
        cantBeNullOrEmpty(code, "The field code should not be null or empty!");
        containOnlyLettersLowerCaseAndDash(code, "The field code must not be out of lowercase letters and dash format!");
        cantBeLessZero(order, "The field order should not be less than zero!");
        isHexadecimal(colorCode, "The field codeColor should not be out of hexadecimal format!");
        this.name = name;
        this.code = code;
        this.shortDescription = shortDescription;
        this.studyGuide = studyGuide;
        this.active = active;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(code, category.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", imageUrl='" + imageUrl + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }

}
