package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.category.CategoryWithSubCategoriesAndCoursesProjection;
import br.com.levelup.aluratech.controller.response.category.ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.CourseRepository;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryApiController {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CourseRepository courseRepository;

    public CategoryApiController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository,
                                 CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.courseRepository = courseRepository;
    }

    @Cacheable(value = "categoriesApi")
    @GetMapping(value = "/api/categories", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse>> allActiveCategories() {
        List<Category> activeCategories = categoryRepository.findAllByActiveTrue();
        List<ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse> activeCategoriesWithActiveSubCategoriesAndPublicCoursesResponse =
                mapActiveCategories(activeCategories);
        mapActiveCategories(activeCategories);
        return ResponseEntity.ok(activeCategoriesWithActiveSubCategoriesAndPublicCoursesResponse);
    }

    private List<ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse> mapActiveCategories(List<Category> activeCategories) {
        List<ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse> activeCategoriesWithActiveSubCategoriesAndPublicCoursesResponse = new ArrayList<>();
        activeCategories.forEach(category -> {
            List<SubCategory> subCategories = subCategoryRepository.findAllByActiveTrueAndCategoryId(category.getId());
            List<Course> courses = courseRepository.findAllByVisibilityTrueAndSubCategory_CategoryId(category.getId());
            activeCategoriesWithActiveSubCategoriesAndPublicCoursesResponse
                    .add(new ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse(category, subCategories, courses));
        });
        return activeCategoriesWithActiveSubCategoriesAndPublicCoursesResponse;
    }

    @GetMapping("/api/categories/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value = "categoriesApi", allEntries = true)
    public String clearCacheApiCategories() {
        return "cache/sucessMessage";
    }

    @GetMapping("/category/{categoryCode}")
    public String publicPageCategories(@PathVariable String categoryCode, Model model) {
        CategoryWithSubCategoriesAndCoursesProjection categoriesWithSubCategoryCourses = categoryRepository.findCategoriesWithSubCategoryAndCourses(categoryCode);
        model.addAttribute("categoriesWithSubCategoryCourses", categoriesWithSubCategoryCourses);
        return "category/publicPageCategory";
    }
}
