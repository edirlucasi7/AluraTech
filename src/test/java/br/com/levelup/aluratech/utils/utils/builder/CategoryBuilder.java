package br.com.levelup.aluratech.utils.utils.builder;

import br.com.levelup.aluratech.model.Category;

public class CategoryBuilder {

    private String name;
    private String code;
    private Integer order = 0;
    private boolean active;

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

    public CategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public Category toEntity() {
        Category category = new Category(name, code);
        category.setOrder(order);
        category.setActive(active);
        return category;
    }
}
