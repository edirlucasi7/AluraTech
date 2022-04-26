package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.category.CategoryWithSubCategoriesAndCoursesProjection;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublicPageCategoriesController {

    private final CategoryRepository categoryRepository;

    public PublicPageCategoriesController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category/{categoryCode}")
    public String publicPageCategories(@PathVariable String categoryCode, Model model) {
        CategoryWithSubCategoriesAndCoursesProjection categoriesWithSubCategoryCourses = categoryRepository.findActiveCategoriesWithSubCategoryAndCourses(categoryCode);
        model.addAttribute("categoriesWithSubCategoryCourses", categoriesWithSubCategoryCourses);
        return "category/publicPageCategory";
    }
}
