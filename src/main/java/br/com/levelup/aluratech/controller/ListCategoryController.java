package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.models.response.CategoryResponse;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
