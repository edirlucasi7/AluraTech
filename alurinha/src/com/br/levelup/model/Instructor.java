package com.br.levelup.model;

import validators.StringValidator;

public class Instructor {

    private String name;

    public Instructor(String name) {
        StringValidator.cantBeNotEmpty(name, "The name should not be empty!");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                '}';
    }

}
