package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.CategoryProjection;
import br.com.levelup.aluratech.controller.projection.CourseProjection;
import br.com.levelup.aluratech.controller.response.course.CourseResponse;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.CourseRepository;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CourseRepository courseRepository;

    public CourseController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository,
                            CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}")
    public String allCourses(@PathVariable String categoryCode, @PathVariable String subcategoryCode, Model model,
                                     @PageableDefault(size = 5) Pageable pageable) {
        Optional<CategoryProjection> possibleCategory = categoryRepository.findCategoryNameByCode(categoryCode);
        Optional<CourseProjection> possibleSubcategory = subCategoryRepository.findSubCategoryNameByCode(subcategoryCode);
        if(possibleCategory.isEmpty() || possibleSubcategory.isEmpty()) {
            return "errors/pageNotFound";
        }
        Page<CourseResponse> coursesBySubCategory = courseRepository.findCoursesBySubCategory(subcategoryCode, pageable);
        model.addAttribute("subcategoryName", possibleSubcategory.get().getName());
        model.addAttribute("coursesBySubCategory", coursesBySubCategory);
        model.addAttribute("categoryCode", categoryCode);
        model.addAttribute("subcategoryCode", subcategoryCode);
        return "course/listCourses";
    }
}
