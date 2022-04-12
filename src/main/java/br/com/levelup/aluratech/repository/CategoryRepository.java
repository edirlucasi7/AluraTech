package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.CategoryProjection;
import br.com.levelup.aluratech.controller.projection.report.ReportOfCoursesByCategoryProjection;
import br.com.levelup.aluratech.controller.response.category.ExistingCategoriesResponse;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.controller.response.category.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveTrue();

    @Query("SELECT new br.com.levelup.aluratech.controller.response.category.CategoryResponse(c.name, c.code, c.active) " +
            "FROM Category c ORDER BY c.order")
    List<CategoryResponse> findAllSorted();

    Optional<Category> findByCode(String code);

    Optional<CategoryProjection> findCategoryNameByCode(String categoryCode);

    @Query("SELECT new br.com.levelup.aluratech.controller.response.category.ExistingCategoriesResponse(c.id, c.name) " +
            "FROM Category c ORDER BY c.name ASC")
    List<ExistingCategoriesResponse> findCategoriesAlphabeticOrder();

    @Query(value = "SELECT ca.name, COALESCE(COUNT(co.id), 0) AS amount " +
            "FROM category ca " +
            "LEFT JOIN subcategory s ON ca.id = s.category_id " +
            "LEFT JOIN course co ON s.id = co.subcategory_id " +
            "WHERE ca.active = true " +
            "GROUP BY ca.name ORDER BY amount DESC", nativeQuery = true)
    List<ReportOfCoursesByCategoryProjection> findAllCoursesByCategory();
}
