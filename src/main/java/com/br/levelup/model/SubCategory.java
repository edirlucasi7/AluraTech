package com.br.levelup.model;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.br.levelup.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "subcategory")
public class SubCategory {

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
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public SubCategory(String name, String code, Category category) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowerCaseAndDash(code);
        cantBeNull(category, "The object category should not be null!");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public void setShortDescription(String shortDescription) {
        cantBeNull(shortDescription);
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

    private boolean verifyIfShortDescriptionIsEmpty() {
        return this.shortDescription == null || this.shortDescription.isEmpty() ? true : false;
    }

    private boolean verifyIfShortDescriptionIsNotEmpty() {
        return this.shortDescription == null || this.shortDescription.isEmpty() ? false : true;
    }

    public static Integer processingOrder(String stringOrder) {
        return "".equals(stringOrder) ? 0 : Integer.parseInt(stringOrder);
    }

    public static List<SubCategory> activeSubCategoriesSortedByOrder(List<SubCategory> subCategories) {
        return subCategories.stream().filter(SubCategory::isActive)
                .sorted(Comparator.comparing(SubCategory::getOrder)).toList();
    }

    public static long totalOfActiveSubCategoriesWithDescription(List<SubCategory> subCategories) {
        List<SubCategory> activeSubCategories = activeSubCategoriesSortedByOrder(subCategories);
        return activeSubCategories.stream().filter(SubCategory::verifyIfShortDescriptionIsNotEmpty).count();
    }

    public static boolean convertToBoolean(String stringActive) {
        cantBeNullOrEmpty(stringActive);
        return "ATIVA".equals(stringActive);
    }

    public static List<SubCategory> subCategoriesWithoutDescription(List<SubCategory> subCategories) {
        cantBeNull(subCategories);
        return subCategories
                .stream()
                .filter(SubCategory::verifyIfShortDescriptionIsEmpty).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, category);
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
