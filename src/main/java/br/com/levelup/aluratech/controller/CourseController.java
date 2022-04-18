package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.instructor.ExistingInstructorsProjection;
import br.com.levelup.aluratech.controller.projection.course.CourseProjection;
import br.com.levelup.aluratech.controller.projection.subcategory.ExistingSubCategoriesProjection;
import br.com.levelup.aluratech.controller.request.NewCourseRequest;
import br.com.levelup.aluratech.controller.request.UpdateCourseRequest;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.CourseRepository;
import br.com.levelup.aluratech.repository.InstructorRepository;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository,
                            CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}")
    public String allCourses(@PathVariable String categoryCode, @PathVariable String subcategoryCode, Model model,
                                     @PageableDefault(size = 5) Pageable pageable) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> possibleSubcategory = subCategoryRepository.findByCode(subcategoryCode);
        if(possibleCategory.isEmpty() || possibleSubcategory.isEmpty()) {
            return "errors/pageNotFound";
        }
        Page<CourseProjection> coursesBySubCategory = courseRepository.getAllBySubCategory(subcategoryCode, pageable);
        model.addAttribute("subcategoryName", possibleSubcategory.get().getName());
        model.addAttribute("coursesBySubCategory", coursesBySubCategory);
        model.addAttribute("categoryCode", categoryCode);
        model.addAttribute("subcategoryCode", subcategoryCode);
        return "course/listCourses";
    }

    @GetMapping("/admin/courses/new")
    @Transactional
    public String showViewNewCourse(NewCourseRequest newCourseRequest, BindingResult bindingResult, Model model) {
        List<ExistingSubCategoriesProjection> subcategories = subCategoryRepository.findSubCategoriesAlphabeticOrder();
        List<ExistingInstructorsProjection> instructors = instructorRepository.findInstructorsAlphabeticOrder();
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("instructors", instructors);
        return "course/formNewCourse";
    }

    @PostMapping("/admin/courses/new")
    @Transactional
    public String newCourse(@Valid NewCourseRequest newCourseRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return showViewNewCourse(newCourseRequest, bindingResult, model);
        }
        SubCategory subCategory = subCategoryRepository.findById(newCourseRequest.getIdSubCategory()).get();
        Instructor instructor = instructorRepository.findById(newCourseRequest.getIdInstructor()).get();
        Course newCourse = newCourseRequest.toEntity(instructor, subCategory);
        courseRepository.save(newCourse);
        return "redirect:/admin/courses/"+newCourse.getCodeCategory()+"/"+newCourse.getCodeSubCategory();
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    public String showViewUpdateCourse(@PathVariable String categoryCode, @PathVariable String subcategoryCode, @PathVariable String courseCode,
                                       UpdateCourseRequest updateCourseRequest, BindingResult bindingResult, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategoryCode);
        Optional<Course> possibleCourse = courseRepository.findByCode(courseCode);
        if(possibleCategory.isEmpty() || possibleSubCategory.isEmpty() || possibleCourse.isEmpty()) {
            return "errors/pageNotFound";
        }
        List<ExistingSubCategoriesProjection> subcategories = subCategoryRepository.findSubCategoriesAlphabeticOrder();
        List<ExistingInstructorsProjection> instructors = instructorRepository.findInstructorsAlphabeticOrder();
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("instructors", instructors);
        model.addAttribute("updateCourseRequest", bindingResult.hasErrors() ? updateCourseRequest :
                new UpdateCourseRequest(possibleCourse.get()));
        return "course/formUpdateCourse";
    }

    @PostMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    @Transactional
    public String updateCourse(@PathVariable String categoryCode, @PathVariable String subcategoryCode, @PathVariable String courseCode,
                                       @Valid UpdateCourseRequest updateCourseRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return showViewUpdateCourse(categoryCode, subcategoryCode, courseCode, updateCourseRequest, bindingResult, model);
        }
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategoryCode);
        Optional<Course> possibleCourse = courseRepository.findByCode(courseCode);
        if(possibleCategory.isEmpty() || possibleSubCategory.isEmpty() || possibleCourse.isEmpty()) {
            return "errors/pageNotFound";
        }
        Instructor instructor = instructorRepository.findById(updateCourseRequest.getIdInstructor()).get();
        SubCategory subCategory = subCategoryRepository.findById(updateCourseRequest.getIdSubCategory()).get();
        possibleCourse.get().update(updateCourseRequest, instructor, subCategory);
        return "redirect:/admin/courses/"+possibleCourse.get().getCodeCategory()+"/"+possibleCourse.get().getCodeSubCategory();
    }
}
