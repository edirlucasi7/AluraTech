package com.br.levelup.model;

import java.util.List;

import static com.br.levelup.model.utils.ValidatorUtils.*;

public class Category {

    private String name;
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    private Integer order;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        cantBeNullOrEmpty(name, "The field name should not be null or empty!");
        cantBeNullOrEmpty(code, "The field code should not be null or empty!");
        containOnlyLettersLowercaseAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        this.name = name;
        this.code = code;
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

    public void setShortDescription(String shortDescription) {
        cantBeNullOrEmpty(shortDescription, "The field shortDescription should not be null or empty!");
        this.shortDescription = shortDescription;
    }

    private boolean isActive() {
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

    public static boolean convertToBoolean(String stringActive) {
        return "ATIVA".equals(stringActive);
    }

    public static Integer processingOrder(String stringOrder) {
        return "".equals(stringOrder) ? 0 : Integer.parseInt(stringOrder);
    }

    public static List<Category> activeCategories(List<Category> categories) {
        return categories.stream().filter(Category::isActive).toList();
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
