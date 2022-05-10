package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.request.NewCategoryRequest;
import br.com.levelup.aluratech.controller.request.UpdateCategoryRequest;
import br.com.levelup.aluratech.controller.response.category.CategoryResponse;
import br.com.levelup.aluratech.controller.validator.category.*;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    private final CheckNewCategoryInvalidColorCodeValidator checkNewCategoryInvalidColorCodeValidator;

    private final CheckUpdateCategoryInvalidColorCodeValidator checkUpdateCategoryInvalidColorCodeValidator;

    private final CheckCategoryUniqueNameForAdditionFormValidator checkCategoryUniqueNameForAdditionFormValidator;

    private final CheckCategoryUniqueNameForEditionFormValidator checkCategoryUniqueNameForEditionFormValidator;

    private final CheckCategoryUniqueCodeForEditionFormValidator checkCategoryUniqueCodeForEditionFormValidator;

    @InitBinder(value = "newCategoryRequest")
    public void initNewCategory(WebDataBinder binder) {
        binder.addValidators(checkNewCategoryInvalidColorCodeValidator, checkCategoryUniqueNameForAdditionFormValidator);
    }

    @InitBinder(value = "updateCategoryRequest")
    public void initUpdateCategory(WebDataBinder binder) {
        binder.addValidators(checkUpdateCategoryInvalidColorCodeValidator, checkCategoryUniqueNameForEditionFormValidator,
                checkCategoryUniqueCodeForEditionFormValidator);
    }

    @GetMapping
    public String allCategories(Model model) {
        List<CategoryResponse> categoriesResponse = categoryRepository.findAllSorted();
        model.addAttribute("categories", categoriesResponse);
        return "category/listCategories";
    }

    @GetMapping("/new")
    public String showViewNewCategory(NewCategoryRequest newCategoryRequest, BindingResult bindingResult) {
        return "category/formNewCategory";
    }

    @PostMapping("/new")
    @Transactional
    public String newCategory(@Valid NewCategoryRequest newCategoryRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return showViewNewCategory(newCategoryRequest, bindingResult);
        }
        Category newCategory = newCategoryRequest.toEntity();
        categoryRepository.save(newCategory);
        return "redirect:/admin/categories";
    }

    @GetMapping("/{code}")
    public String showViewUpdateCategory(@PathVariable String code, UpdateCategoryRequest updateCategoryRequest,
                                         BindingResult bindingResult, Model model) {
        Optional<Category> possibleCategory = categoryRepository.findByCode(code);
        if(possibleCategory.isEmpty()) {
            return "errors/pageNotFound";
        }
        model.addAttribute("updateCategoryRequest", bindingResult.hasErrors() ? updateCategoryRequest :
                new UpdateCategoryRequest(possibleCategory.get()));
        return "category/formUpdateCategory";
    }

    @PostMapping("/{code}")
    @Transactional
    public String updateCategory(@PathVariable String code, @Valid UpdateCategoryRequest updateCategoryRequest,
                                 BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return showViewUpdateCategory(code, updateCategoryRequest, bindingResult, model);
        }
        Optional<Category> possibleCategory = categoryRepository.findByCode(code);
        if(possibleCategory.isEmpty()) {
            return "errors/pageNotFound";
        }
        possibleCategory.get().update(updateCategoryRequest);
        return "redirect:/admin/categories";
    }

    @PostMapping("/disable-category/{idCategory}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void disableCategory(@PathVariable Long idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        category.get().disableActive();
    }
}
