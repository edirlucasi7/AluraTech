package com.br.levelup.model;

import validators.StringValidator;

public class SubCategory {

    private String name;
    private String code;
    private String shortDescription;
    private String bigDescriptionGuide;
    private Boolean active;
    private Integer order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        StringValidator.cantBeNotEmpty(name, "The field name should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be empty!");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public SubCategory(String name, String code, String shortDescription, String bigDescriptionGuide, Boolean active, Integer order, Category category) {
        this(name, code, category);
        this.shortDescription = shortDescription;
        this.bigDescriptionGuide = bigDescriptionGuide;
        this.active = active;
        this.order = order;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", bigDescription='" + bigDescriptionGuide + '\'' +
                ", indication=" + active +
                ", order=" + order +
                ", category=" + category +
                '}';
    }

}
