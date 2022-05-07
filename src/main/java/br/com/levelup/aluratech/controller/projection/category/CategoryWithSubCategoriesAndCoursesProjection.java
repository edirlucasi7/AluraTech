package br.com.levelup.aluratech.controller.projection.category;

import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.SubCategory;

import java.util.List;
import java.util.stream.Collectors;

public interface CategoryWithSubCategoriesAndCoursesProjection {
    String getName();
    String getCode();
    String getImageUrl();
    List<SubCategory> getSubCategories();

    default List<SubCategory> getActiveSubCategoriesWithVisibleCourses() {
        return getSubCategories().stream().filter(SubCategory::isActive)
                .filter(s -> s.getCourses().stream().anyMatch(Course::isVisibility)).collect(Collectors.toList());
    }
}
