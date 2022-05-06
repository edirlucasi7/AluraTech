package br.com.levelup.aluratech.controller.projection.category;

import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.SubCategory;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public interface CategoriesWithSubCategoriesProjection {
    String getName();
    String getCode();
    String getImageUrl();
    List<SubCategory> getSubCategories();

    default List<SubCategory> getActiveSubCategoriesWithVisibleCoursesSortedBySubCategory() {
        List<SubCategory> activeSubCategories = getSubCategories().stream()
                .filter(SubCategory::isActive)
                .filter(s -> s.getCourses().stream().anyMatch(Course::isVisibility))
                .collect(Collectors.toList());
        activeSubCategories.sort(comparing(SubCategory::getOrder, nullsFirst(naturalOrder())));
        return activeSubCategories;
    }
}
