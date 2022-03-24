package com.br.levelup.model.utils.builders;

import com.br.levelup.model.Instructor;

public class InstructorBuilder {

    private String name;

    public InstructorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Instructor toEntity() {
        return new Instructor(name);
    }
}
