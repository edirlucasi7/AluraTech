package br.com.levelup.aluratech.controller.response.category;

public class NamesOfCategoriesResponse {

    private String nameOfCategories;

    @Deprecated
    public NamesOfCategoriesResponse() {}

    public String getNameOfCategories() {
        return nameOfCategories;
    }

    public void setNameOfCategories(String nameOfCategories) {
        this.nameOfCategories = nameOfCategories;
    }
}
