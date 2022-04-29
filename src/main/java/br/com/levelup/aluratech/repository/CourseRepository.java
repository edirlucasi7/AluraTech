package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.course.CourseProjection;
import br.com.levelup.aluratech.controller.projection.report.ReportOfCoursesByCategoryProjection;
import br.com.levelup.aluratech.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByVisibilityTrueAndSubCategoryActive_CategoryId(Long idCategory);

    @Query(value = """
            SELECT co.name, co.code, co.visibility
            FROM course `co` 
            INNER JOIN subcategory s ON co.subcategory_id = s.id
            INNER JOIN category ca ON s.category_id = ca.id
            WHERE s.code = :subcategoryCode
            """, nativeQuery = true)
    Page<CourseProjection> getAllBySubCategory(String subcategoryCode, Pageable pageable);

    Optional<Course> findByCode(String courseCode);

    @Query(value = """
            SELECT ca.name, COALESCE(COUNT(co.id), 0) AS amount
            FROM category ca
            LEFT JOIN subcategory s ON ca.id = s.category_id
            LEFT JOIN course co ON s.id = co.subcategory_id
            GROUP BY ca.name ORDER BY amount DESC
            """, nativeQuery = true)
    List<ReportOfCoursesByCategoryProjection> findAllCoursesByCategory();
}
