package br.com.levelup.aluratech.utils.utils.builder;

import br.com.levelup.aluratech.model.Instructor;

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
