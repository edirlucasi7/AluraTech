package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.category.ExistingCategoriesProjection;
import br.com.levelup.aluratech.controller.projection.subcategory.SubCategoryProjection;
import br.com.levelup.aluratech.controller.request.NewSubCategoryRequest;
import br.com.levelup.aluratech.controller.request.UpdateSubCategoryRequest;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class SubCategoryController {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @GetMapping("/admin/subcategories/{categoryCode}")
    public String allSubCategories(@PathVariable String categoryCode, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        if(possibleCategory.isEmpty()) {
            return "errors/pageNotFound";
        }
        List<SubCategoryProjection> subCategories = subCategoryRepository.findAllSorted(categoryCode);
        model.addAttribute("categoryName", possibleCategory.get().getName());
        model.addAttribute("categoryCode", categoryCode);
        model.addAttribute("subCategories", subCategories);
        return "subcategory/listSubCategories";
    }

    @GetMapping("/admin/subcategories/new")
    public String showViewNewSubCategory(NewSubCategoryRequest newSubCategoryRequest, BindingResult bindingResult, Model model) {
        List<ExistingCategoriesProjection> categories = categoryRepository.findAllByOrderByName();
        model.addAttribute("categories", categories);
        return "subcategory/formNewSubCategory";
    }

    @PostMapping("/admin/subcategories/new")
    @Transactional
    public String newSubCategory(@Valid NewSubCategoryRequest newSubCategoryRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return showViewNewSubCategory(newSubCategoryRequest, bindingResult, model);
        }
        Category category = categoryRepository.findById(newSubCategoryRequest.getIdCategory()).get();
        SubCategory newSubCategory = newSubCategoryRequest.toEntity(category);
        subCategoryRepository.save(newSubCategory);
        return "redirect:/admin/subcategories/"+newSubCategory.getCategoryCode();
    }

    @GetMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String showViewUpdateSubCategory(@PathVariable String categoryCode, @PathVariable String subcategoryCode,
                                            UpdateSubCategoryRequest updateSubCategoryRequest, BindingResult bindingResult, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategoryCode);
        if(possibleCategory.isEmpty() || possibleSubCategory.isEmpty()) {
            return "errors/pageNotFound";
        }
        List<ExistingCategoriesProjection> categories = categoryRepository.findAllByOrderByName();
        model.addAttribute("categories", categories);
        model.addAttribute("updateSubCategoryRequest", bindingResult.hasErrors() ? updateSubCategoryRequest :
                new UpdateSubCategoryRequest(possibleSubCategory.get()));
        return "subcategory/formUpdateSubCategory";
    }

    @PostMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    @Transactional
    public String updateSubCategory(@PathVariable String categoryCode, @PathVariable String subcategoryCode,
                                    @Valid UpdateSubCategoryRequest updateSubCategoryRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return showViewUpdateSubCategory(categoryCode, subcategoryCode, updateSubCategoryRequest, bindingResult, model);
        }
        Optional<Category> possibleCategory = categoryRepository.findByCode(categoryCode);
        Optional<SubCategory> possibleSubCategory = subCategoryRepository.findByCode(subcategoryCode);
        if(possibleCategory.isEmpty() || possibleSubCategory.isEmpty()) {
            return "errors/pageNotFound";
        }
        Category category = categoryRepository.findById(updateSubCategoryRequest.getIdCategory()).get();
        possibleSubCategory.get().update(updateSubCategoryRequest, category);
        return "redirect:/admin/subcategories/"+categoryCode;
    }

    @PostMapping("/admin/subcategories/disable-subcategory/{idSubCategory}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void disableSubCategory(@PathVariable Long idSubCategory) {
        Optional<SubCategory> subCategory = subCategoryRepository.findById(idSubCategory);
        subCategory.get().disableActive();
    }
}
