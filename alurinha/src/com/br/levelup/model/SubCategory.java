package com.br.levelup.model;

import java.util.Comparator;
import java.util.List;

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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        cantBeLessZero(order, "The field order should not be null or less than zero!");
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public Integer getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public String getCategoryCode() {
        return category.getCode();
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isActive() {
        return active;
    }

    public static String verifyDescriptionEmpty(String description) {
        return "".equals(description) ? "Uninformed description" : description;
    }

    public static Integer processingOrder(String stringOrder) {
        return "".equals(stringOrder) ? 0 : Integer.parseInt(stringOrder);
    }

    public static List<SubCategory> activeSubCategories(List<SubCategory> subCategories) {
        return subCategories.stream().filter(SubCategory::isActive)
                .sorted(Comparator.comparing(SubCategory::getOrder)).toList();
    }

    public static boolean convertToBoolean(String stringActive) {
        cantBeNullOrEmpty(stringActive);
        return "ATIVA".equals(stringActive);
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
