package com.br.levelup.model;

import validators.StringValidator;

public class Category {

    private String name;
    private String code;
    private String shortDescription;
    private String bigDescription;
    private Boolean active = false;
    private Integer order;
    private String iconImagePath;
    private String codeHtlmColor;

    public Category(String name, String code) {
        StringValidator.cantBeNotEmpty(name, "The field name should not be empty!");
        StringValidator.containOnlyLettersLowercaseAndNumbersAndDash(code, "he field code should be out of format!");
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String shortDescription, String bigDescriptio,
                    Integer order, String iconImagePath, String codeHtlmColor) {
        this(name, code);
        this.shortDescription = shortDescription;
        this.bigDescription = bigDescription;
        this.order = order;
        this.iconImagePath = iconImagePath;
        StringValidator.isHexadecimal(codeHtlmColor, "The field codeHtlmColor should not be out of hexadecimal format!");
        this.codeHtlmColor = codeHtlmColor;
    }

    public Category(String name, String code, String shortDescription, String bigDescription, Boolean active,
                    Integer order, String iconImagePath, String codeHtlmColor) {
        this(name, code);
        this.shortDescription = shortDescription;
        this.bigDescription = bigDescription;
        this.active = active;
        this.order = order;
        this.iconImagePath = iconImagePath;
        StringValidator.isHexadecimal(codeHtlmColor, "The field codeHtlmColor should not be out of hexadecimal format!");
        this.codeHtlmColor = codeHtlmColor;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", bigDescription='" + bigDescription + '\'' +
                ", indication=" + active +
                ", order=" + order +
                ", iconImagePath='" + iconImagePath + '\'' +
                ", codeHtlmColor='" + codeHtlmColor + '\'' +
                '}';
    }

}
