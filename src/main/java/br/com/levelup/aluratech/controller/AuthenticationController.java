package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.category.CategoriesWithSubCategoriesProjection;
import br.com.levelup.aluratech.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthenticationController {

    private final CategoryRepository categoryRepository;

    public AuthenticationController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/login")
    public String login(Model model) {
        List<CategoriesWithSubCategoriesProjection> categories = categoryRepository.findActiveCategories();
        model.addAttribute("categories", categories);
        return "login/login";
    }
}
