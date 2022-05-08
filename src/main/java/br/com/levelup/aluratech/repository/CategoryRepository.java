package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.category.ExistingCategoriesProjection;
import br.com.levelup.aluratech.controller.projection.category.CategoriesWithSubCategoriesProjection;
import br.com.levelup.aluratech.controller.projection.category.CategoryWithSubCategoriesAndCoursesProjection;
import br.com.levelup.aluratech.controller.response.category.CategoryResponse;
import br.com.levelup.aluratech.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveTrue();

    @Query("SELECT new br.com.levelup.aluratech.controller.response.category.CategoryResponse(c.id, c.name, c.code, c.active) " +
            "FROM Category c ORDER BY c.order")
    List<CategoryResponse> findAllSorted();

    Optional<Category> findByCode(String code);

    List<ExistingCategoriesProjection> findAllByOrderByName();

    @Query(value = """
        SELECT DISTINCT ca
        FROM Category ca 
        WHERE ca.active = true
        """)
    List<CategoriesWithSubCategoriesProjection> findActiveCategories();

    @Query(value = """
        SELECT DISTINCT ca
        FROM Category ca 
        WHERE ca.active = true 
        AND ca.code = :categoryCode
        """)
    Optional<CategoryWithSubCategoriesAndCoursesProjection> findActiveCategories(String categoryCode);

    boolean existsByName(String name);

    @Deprecated
    boolean existsByNameAndIdNot(String name, Long id);

    default boolean existsByNameWithDifferentId(String name, Long id) {
        return existsByNameAndIdNot(name, id);
    }

    boolean existsByCodeAndIdNot(String code, Long id);

    default boolean existsByCodeWithDifferentId(String code, Long id) {
        return existsByCodeAndIdNot(code, id);
    }
}
