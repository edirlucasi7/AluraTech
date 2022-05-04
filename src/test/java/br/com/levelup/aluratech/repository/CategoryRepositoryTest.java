package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.category.CategoriesWithSubCategoriesProjection;
import br.com.levelup.aluratech.controller.projection.category.CategoryWithSubCategoriesAndCoursesProjection;
import br.com.levelup.aluratech.controller.response.category.CategoryResponse;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.utils.builder.CategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityManager manager;

    private Category activeCategory;

    private Category inactiveCategory;

    @BeforeEach
    public void beforeEach() {
        this.activeCategory = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java")
                .withImageUrl("http://teste")
                .withOrder(1)
                .withActive(true)
                .toEntity();
        manager.persist(activeCategory);

        this.inactiveCategory = new CategoryBuilder()
                .withName("Devops")
                .withCode("devops")
                .withImageUrl("http://teste")
                .toEntity();
        manager.persist(inactiveCategory);
    }

    @Test
    void should_retrieve_all_categories_sorted_by_order() {
        Category activeCategory2 = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .withOrder(2)
                .withActive(true)
                .toEntity();

        manager.persist(activeCategory2);

        List<CategoryResponse> categories = categoryRepository.findAllSorted();

        assertThat(categories)
                .isNotEmpty()
                .hasSize(3)
                .extracting(CategoryResponse::getCode).containsExactly("devops", "java", "java-oo")
                .doesNotContainNull();
    }

    @Test
    public void should_retrieve_all_active_categories() {
        List<CategoriesWithSubCategoriesProjection> categories = categoryRepository.findActiveCategories();
        assertThat(categories)
                .extracting("name", "code", "imageUrl")
                .contains(tuple("Programacao", "java", "http://teste"))
                .doesNotContain(tuple("Devops", "devops", "http://teste"))
                .hasSize(1);
    }

    @Test
    public void should_retrieve_active_category_by_code() {
        Optional<CategoryWithSubCategoriesAndCoursesProjection> category = categoryRepository.findActiveCategories("java");

        assertThat(category.get())
                .extracting("code")
                .contains("java")
                .doesNotContain("devops")
                .hasSize(1);
    }

    @Test
    public void should_retrieve_a_list_of_active_categories_empty() {
        Optional<CategoryWithSubCategoriesAndCoursesProjection> category = categoryRepository.findActiveCategories("devops");

        assertThat(category).isEmpty();
    }
}
