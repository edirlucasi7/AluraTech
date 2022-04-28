package br.com.levelup.aluratech.utils.repository;

import br.com.levelup.aluratech.controller.projection.subcategory.SubCategoryProjection;
import br.com.levelup.aluratech.controller.response.category.CategoryResponse;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.repository.SubCategoryRepository;
import br.com.levelup.aluratech.utils.utils.builder.CategoryBuilder;
import br.com.levelup.aluratech.utils.utils.builder.SubCategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SubCategoryRepositoryTest {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private EntityManager manager;

    private Category activeCategory;

    @BeforeEach
    public void beforeEach() {
        this.activeCategory = new CategoryBuilder()
                .withName("Programacao")
                .withCode("programacao")
                .withOrder(1)
                .withActive(true)
                .toEntity();
        manager.persist(activeCategory);
    }

    @Test
    void should_retrieve_all_categories_sorted_by_order() {
        SubCategory subCategoryActive = new SubCategoryBuilder()
                .withName("PHP")
                .withCode("php")
                .withActive(true)
                .withCategory(activeCategory)
                .toEntity();
        manager.persist(subCategoryActive);

        SubCategory inactiveSubCategory = new SubCategoryBuilder()
                .withName("Java")
                .withCode("java")
                .withCategory(activeCategory)
                .toEntity();
        manager.persist(inactiveSubCategory);

        List<SubCategoryProjection> subCategoriesByCategory = subCategoryRepository.findAllSorted("programacao");

        assertThat(subCategoriesByCategory)
                .extracting("name", "code")
                .containsExactly(tuple("PHP", "php"),
                        tuple("Java", "java"))
                .hasSize(2);
    }
}
