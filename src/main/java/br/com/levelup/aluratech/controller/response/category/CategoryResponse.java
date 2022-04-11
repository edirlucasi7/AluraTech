package br.com.levelup.aluratech.controller.response.category;

public class CategoryResponse {

    private final String name;
    private final String code;
    private final boolean active;

    public CategoryResponse(String name, String code, boolean active) {
        this.name = name;
        this.code = code;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }
}
