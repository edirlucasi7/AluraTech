package com.br.levelup.model.utils.builders;

import com.br.levelup.model.Category;
import com.br.levelup.model.SubCategory;

public class SubCategoryBuilder {

    private String name;
    private String code;
    private Category category;
    private boolean active;

    public SubCategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SubCategoryBuilder withCode(String code) {
        this.code = code;
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
        subCategory.setActive(active);
        return subCategory;
    }

}
