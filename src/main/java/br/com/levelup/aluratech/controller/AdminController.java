package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.controller.projection.report.ReportInstructorWithMoreCoursesProjection;
import br.com.levelup.aluratech.controller.projection.report.ReportOfCoursesByCategoryProjection;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.CourseRepository;
import br.com.levelup.aluratech.repository.InstructorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public AdminController(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("/admin")
    public String redirectReport() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String report(Model model) {
        List<ReportOfCoursesByCategoryProjection> allCoursesByCategory = courseRepository.findAllCoursesByCategory();
        Optional<ReportInstructorWithMoreCoursesProjection> possibleInstructorWithMoreCourses = instructorRepository.findInstructorWithMoreCourses();
        if(possibleInstructorWithMoreCourses.isPresent()) {
            model.addAttribute("instructorWithMoreCourses", possibleInstructorWithMoreCourses.get());
        }
        model.addAttribute("allCoursesByCategory", allCoursesByCategory);
        return "report/dashboardAdmin";
    }
}
