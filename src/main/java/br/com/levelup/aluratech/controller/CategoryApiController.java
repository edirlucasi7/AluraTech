package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.model.response.ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.CourseRepository;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
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

    @GetMapping(value = "/api/categories", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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
            List<Course> courses = courseRepository.findPublicCoursesByCategoryId(category.getId());
            activeCategoriesWithActiveSubCategoriesAndPublicCoursesResponse
                    .add(new ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse(category, subCategories, courses));
        });
        return activeCategoriesWithActiveSubCategoriesAndPublicCoursesResponse;
    }
}
