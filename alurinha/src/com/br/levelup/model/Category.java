package com.br.levelup.model;

import com.br.levelup.model.enums.Indication;
import validators.StringValidator;

public class Category {

    private String name;
    private String code;
    private String shortDescription;
    private String bigDescription;
    private Indication indication = Indication.INATIVA;
    private Integer order;
    private String iconImagePath;
    private String codeHtlmColor;

    public Category(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String shortDescription, String bigDescription, Indication indication,
                    Integer order, String iconImagePath, String codeHtlmColor) {
        this(name, code);
        this.shortDescription = shortDescription;
        this.bigDescription = bigDescription;
        this.indication = indication;
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
                ", indication=" + indication +
                ", order=" + order +
                ", iconImagePath='" + iconImagePath + '\'' +
                ", codeHtlmColor='" + codeHtlmColor + '\'' +
                '}';
    }

}
