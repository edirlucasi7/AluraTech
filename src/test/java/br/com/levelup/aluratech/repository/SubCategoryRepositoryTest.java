package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.subcategory.SubCategoryProjection;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.utils.builder.CategoryBuilder;
import br.com.levelup.aluratech.utils.builder.SubCategoryBuilder;
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

    private Category category;

    @BeforeEach
    public void beforeEach() {
        this.category = new CategoryBuilder()
                .withName("Programacao")
                .withCode("programacao")
                .withOrder(1)
                .withActive(true)
                .toEntity();
        manager.persist(category);
    }

    @Test
    void should_retrieve_all_subcategories_sorted_by_order() {
        SubCategory subCategory1 = new SubCategoryBuilder()
                .withName("PHP")
                .withCode("php")
                .withActive(true)
                .withOrder(2)
                .withCategory(category)
                .toEntity();
        manager.persist(subCategory1);

        SubCategory subCategory2 = new SubCategoryBuilder()
                .withName("Java")
                .withCode("java")
                .withOrder(1)
                .withCategory(category)
                .toEntity();
        manager.persist(subCategory2);

        List<SubCategoryProjection> subCategoriesByCategory = subCategoryRepository.findAllSorted("programacao");

        assertThat(subCategoriesByCategory)
                .extracting("name", "code")
                .containsExactly(tuple("Java", "java"),
                        tuple("PHP", "php"))
                .hasSize(2);
    }
}
