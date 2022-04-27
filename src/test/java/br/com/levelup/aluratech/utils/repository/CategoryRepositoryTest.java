package br.com.levelup.aluratech.utils.repository;

import br.com.levelup.aluratech.controller.response.category.CategoryResponse;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.repository.CategoryRepository;
import br.com.levelup.aluratech.utils.utils.builder.CategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityManager manager;

    @Test
    void should_retrieve_all_categories_sorted_by_order() {
        Category activeCategory1 = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java")
                .withOrder(1)
                .withActive(true)
                .toEntity();

        Category activeCategory2 = new CategoryBuilder()
                .withName("Programacao")
                .withCode("java-oo")
                .withOrder(2)
                .withActive(true)
                .toEntity();

        Category inactiveCategory = new CategoryBuilder()
                .withName("Devops")
                .withCode("docker")
                .toEntity();

        manager.persist(activeCategory1);
        manager.persist(activeCategory2);
        manager.persist(inactiveCategory);

        List<CategoryResponse> categories = categoryRepository.findAllSorted();
        System.out.println(categories);
        assertTrue(categories.size() == 3);
        assertTrue(categories.get(0).getCode().equals("docker"));
        assertTrue(categories.get(1).getCode().equals("java"));
        assertTrue(categories.get(2).getCode().equals("java-oo"));
    }

}
