package com.br.levelup.model.dto;

import com.br.levelup.model.SubCategory;

import java.util.List;
import java.util.stream.Collectors;

public class SubCategoryDTO {

    private final String name;
    private final String code;
    private final String shortDescription;
    private final boolean active;
    private final Integer order;
    private final Long idCategory;

    public SubCategoryDTO(SubCategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.shortDescription = subcategory.getShortDescription();
        this.active = subcategory.isActive();
        this.order = subcategory.getOrder();
        this.idCategory = subcategory.getCategoryId();
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

    public Long getIdCategory() {
        return idCategory;
    }

    public static List<SubCategoryDTO> convert(List<SubCategory> categories) {
        return categories.stream().map(SubCategoryDTO::new).collect(Collectors.toList());
    }

}
