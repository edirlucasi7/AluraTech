package com.br.levelup.model;

import static com.br.levelup.model.utils.ValidatorUtils.*;

public class SubCategory {

    private String name;
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    private Integer order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be empty!");
        cantBeNull(category, "The object category should not be null!");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public void setShortDescription(String shortDescription) {
        cantBeNullOrEmpty(shortDescription, "The field shortDescription should not be empty!");
        this.shortDescription = shortDescription;
    }

    public void setStudyGuide(String studyGuide) {
        cantBeNullOrEmpty(studyGuide, "The field studyGuide should not be empty!");
        this.studyGuide = studyGuide;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        cantBeLessOrEqualZero(order, "The field order should not be null or less than zero!");
        this.order = order;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", indication=" + active +
                ", order=" + order +
                ", category=" + category +
                '}';
    }

}
