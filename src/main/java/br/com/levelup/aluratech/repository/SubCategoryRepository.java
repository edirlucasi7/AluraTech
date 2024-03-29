package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.subcategory.ExistingSubCategoriesProjection;
import br.com.levelup.aluratech.controller.projection.subcategory.SubCategoryProjection;
import br.com.levelup.aluratech.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findAllByActiveTrueAndCategoryId(Long categoryId);

    @Query(value = """
            SELECT s.id, s.name, s.code, s.active
            FROM subcategory s 
            INNER JOIN category c ON s.category_id = c.id
            WHERE c.code = :categoryCode 
            ORDER BY s.order_visualization
            """, nativeQuery = true)
    List<SubCategoryProjection> findAllSorted(String categoryCode);

    Optional<SubCategory> findByCode(String subCategoryCode);

    List<ExistingSubCategoriesProjection> findAllByOrderByName();

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    default boolean existsByNameWithDifferentId(String name, Long id) {
        return existsByNameAndIdNot(name, id);
    }

    boolean existsByCodeAndIdNot(String code, Long id);

    default boolean existsByCodeWithDifferentId(String code, Long id) {
        return existsByCodeAndIdNot(code, id);
    }
}
