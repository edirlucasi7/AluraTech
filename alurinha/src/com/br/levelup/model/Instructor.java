package com.br.levelup.model;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

public class Instructor {

    private String name;

    public Instructor(String name) {
        cantBeNullOrEmpty(name, "The field name should not be null or empty!");
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
