package com.br.levelup.model.utils.builders;

import com.br.levelup.model.Category;
import com.br.levelup.model.SubCategory;

public class SubCategoryBuilder {

    private String name;
    private String code;
    private boolean active;
    private String shortDescription;
    private Integer order = 0;
    private Category category;

    public SubCategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SubCategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public SubCategoryBuilder withOrder(Integer order) {
        this.order = order;
        return this;
    }

    public SubCategoryBuilder withShorDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public SubCategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public SubCategoryBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public SubCategory toEntity() {
        SubCategory subCategory = new SubCategory(name, code, category);
        subCategory.setOrder(order);
        subCategory.setShortDescription(shortDescription);
        subCategory.setActive(active);
        return subCategory;
    }

}
