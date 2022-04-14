package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.course.CourseProjection;
import br.com.levelup.aluratech.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByVisibilityTrueAndSubCategory_CategoryId(Long idCategory);

    @Query(value = """
            SELECT co.name, co.code, co.visibility
            FROM course `co` 
            INNER JOIN subcategory s ON co.subcategory_id = s.id
            INNER JOIN category ca ON s.category_id = ca.id
            WHERE s.code = :subcategoryCode
            """, nativeQuery = true)
    Page<CourseProjection> getAllBySubCategory(String subcategoryCode, Pageable pageable);
}
