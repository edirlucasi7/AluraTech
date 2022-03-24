package com.br.levelup.model.utils.builders;

import com.br.levelup.model.Category;

public class CategoryBuilder {

    private String name;
    private String code;
    private Integer order = 0;

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder withOrder(Integer order) {
        this.order = order;
        return this;
    }

    public Category toEntity() {
        Category category = new Category(name, code);
        category.setOrder(order);
        return category;
    }
}
