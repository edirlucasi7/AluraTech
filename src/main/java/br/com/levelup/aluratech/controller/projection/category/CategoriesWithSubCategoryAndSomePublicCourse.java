package br.com.levelup.aluratech.controller.projection.category;

import br.com.levelup.aluratech.model.SubCategory;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CategoriesWithSubCategoryAndSomePublicCourse {
    @Value("#{target.name}")
    String getName();
    @Value("#{target.code}")
    String getCode();
    @Value("#{target.imageUrl}")
    String getImageUrl();
    @Value("#{target.subCategories}")
    List<SubCategory> getSubCategories();
}
