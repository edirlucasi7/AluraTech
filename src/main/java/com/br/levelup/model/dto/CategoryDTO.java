package com.br.levelup.model.dto;

import com.br.levelup.model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {

    private final Long id;
    private final String name;
    private final String code;
    private final String shortDescription;
    private final boolean active;
    private final String studyGuide;
    private final Integer order;
    private final String imageUrl;
    private final String colorCode;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.shortDescription = category.getShortDescription();
        this.active = category.isActive();
        this.studyGuide = category.getStudyGuide();
        this.order = category.getOrder();
        this.imageUrl = category.getImageUrl();
        this.colorCode = category.getColorCode();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getOrder() {
        return order;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public static List<CategoryDTO> convert(List<Category> categories) {
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

}
