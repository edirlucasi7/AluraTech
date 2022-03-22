package com.br.levelup.model.utils.builders;

import com.br.levelup.model.Category;

public class CategoryBuilder {

    private String name;
    private String code;

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public Category toEntity() {
        return new Category(name, code);
    }
}
