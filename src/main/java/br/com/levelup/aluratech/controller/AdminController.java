package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.report.ReportInstructorWithMoreCourses;
import br.com.levelup.aluratech.controller.projection.report.ReportOfCoursesByCategoryProjection;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.InstructorRepository;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final CategoryRepository categoryRepository;
    private final InstructorRepository instructorRepository;

    public AdminController(CategoryRepository categoryRepository, InstructorRepository instructorRepository) {
        this.categoryRepository = categoryRepository;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("/admin")
    public String redirectReport(Model model) {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String report(Model model) {
        List<ReportOfCoursesByCategoryProjection> allCoursesByCategory = categoryRepository.findAllCoursesByCategory();
        List<ReportInstructorWithMoreCourses> instructorWithMoreCourses = instructorRepository.findInstructorWithMoreCourses();
        model.addAttribute("allCoursesByCategory", allCoursesByCategory);
        model.addAttribute("instructorWithMoreCourses", instructorWithMoreCourses);
        return "report/dashboardAdmin";
    }
}
