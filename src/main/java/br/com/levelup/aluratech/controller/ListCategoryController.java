package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.models.Category;
import br.com.levelup.aluratech.models.request.NewCategoryRequest;
import br.com.levelup.aluratech.models.response.CategoryResponse;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ListCategoryController {

    private final CategoryRepository categoryRepository;

    public ListCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/categories")
    public String allCategories(Model model) {
        List<CategoryResponse> categoriesResponse = categoryRepository.findAllSorted();
        model.addAttribute("categories", categoriesResponse);
        return "listCategories";
    }

    @GetMapping("/admin/categories/new")
    public String showView() {
        return "formNewAndUpdateCategory";
    }

    @PostMapping("/admin/categories/new")
    public String newCategory(@Valid NewCategoryRequest newCategoryRequest, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
//            return "formNewAndUpdateCategory";
//        }
        Category newCategory = newCategoryRequest.toEntity();
        categoryRepository.save(newCategory);
        return "redirect:/admin/categories";
    }
}
