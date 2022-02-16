package com.br.levelup.model;

import validators.StringValidator;

public class Instructor {

    private String name;

    public Instructor(String name) {
        StringValidator.cantBeNullOrEmpty(name, "The field name should not be empty!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                '}';
    }

}
