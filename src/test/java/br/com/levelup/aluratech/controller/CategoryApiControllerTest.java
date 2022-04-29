package br.com.levelup.aluratech.controller;

import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.repository.CourseRepository;
import br.com.levelup.aluratech.repository.InstructorRepository;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @AfterEach
    public void afterEach() {
        courseRepository.deleteAll();
        instructorRepository.deleteAll();
        subCategoryRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void should_retrieve_all_active_categories() throws Exception {
        Category category = new Category("Programacao", "programacao", "descricao",
                "teste", 1, true, "http://teste", "#00c86f");
        categoryRepository.save(category);

        SubCategory subCategory = new SubCategory("Java", "java", category);
        subCategory.setActive(true);
        subCategory.setStudyGuide("teste");
        subCategoryRepository.save(subCategory);

        Instructor instructor = new Instructor("Madu");
        instructorRepository.save(instructor);

        Course course = new Course("Java Iniciante", "java-iniciante", 8, instructor, subCategory);
        course.setVisibility(true);
        course.setDevelopedSkills("teste");
        courseRepository.save(course);

        mockMvc.perform(get("/api/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.[0].name", is("Programacao")))
                .andExpect(jsonPath("$.[0].code", is("programacao")))
                .andExpect(jsonPath("$.[0].order", is(1)))
                .andExpect(jsonPath("$.[0].studyGuide", is("teste")))
                .andExpect(jsonPath("$.[0].colorCode", is("#00c86f")))
                .andExpect(jsonPath("$.[0].totalOfCoursesByCategory", is(1)))
                .andExpect(jsonPath("$.[0].subCategories[0].name", is("Java")))
                .andExpect(jsonPath("$.[0].subCategories[0].code", is("java")))
                .andExpect(jsonPath("$.[0].subCategories[0].studyGuide", is("teste")))
                .andExpect(jsonPath("$.[0].courses[0].name", is("Java Iniciante")))
                .andExpect(jsonPath("$.[0].courses[0].code", is("java-iniciante")))
                .andExpect(jsonPath("$.[0].courses[0].developedSkills", is("teste")))
                .andExpect(jsonPath("$.[0].courses[0].estimatedTimeInHours", is(8)));
    }
}
