package com.br.levelup.model;

import static validators.IntegerValidator.cantBeLessOrEqualZeroOrNull;
import static validators.StringValidator.*;

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
        containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        this.name = name;
        this.code = code;
    }

    public void setShortDescription(String shortDescription) {
        cantBeNullOrEmpty(shortDescription, "The field shortDescription should not be null or empty!");
        this.shortDescription = shortDescription;
    }

    public void setStudyGuide(String studyGuide) {
        cantBeNullOrEmpty(studyGuide, "The field studyGuide should not be null or empty!");
        this.studyGuide = studyGuide;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        cantBeLessOrEqualZeroOrNull(order, "The field order should not be less than zero or null!");
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
