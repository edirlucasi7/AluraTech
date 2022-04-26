package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.category.ExistingCategoriesProjection;
import br.com.levelup.aluratech.controller.projection.category.CategoriesWithSubCategoriesProjection;
import br.com.levelup.aluratech.controller.projection.category.CategoryWithSubCategoriesAndCoursesProjection;
import br.com.levelup.aluratech.controller.projection.report.ReportOfCoursesByCategoryProjection;
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
            SELECT ca.name, COALESCE(COUNT(co.id), 0) AS amount
            FROM category ca
            LEFT JOIN subcategory s ON ca.id = s.category_id
            LEFT JOIN course co ON s.id = co.subcategory_id
            GROUP BY ca.name ORDER BY amount DESC
            """, nativeQuery = true)
    List<ReportOfCoursesByCategoryProjection> findAllCoursesByCategory();

    @Query(value = """
        SELECT DISTINCT ca
        FROM Category ca 
        WHERE ca.active = true
        """)
    List<CategoriesWithSubCategoriesProjection> findActiveCategoriesWithSubCategories();

    @Query(value = """
        SELECT DISTINCT ca
        FROM Category ca 
        WHERE ca.active = true AND ca.code = :categoryCode
        """)
    CategoryWithSubCategoriesAndCoursesProjection findActiveCategoriesWithSubCategoryAndCourses(String categoryCode);
}
