package com.br.levelup.model;

import java.util.Objects;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

public class Instructor {

    private String name;

    public Instructor(String name) {
        cantBeNullOrEmpty(name, "The field name should not be null or empty!");
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                '}';
    }
}
