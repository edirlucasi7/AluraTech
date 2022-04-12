package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.response.course.CourseResponse;
import br.com.levelup.aluratech.model.Course;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByVisibilityTrueAndSubCategory_CategoryId(Long idCategory);

    @Query(value = """
            SELECT new br.com.levelup.aluratech.controller.response.course.CourseResponse(c.name, c.code, c.visibility,
            c.subCategory.category.code, c.subCategory.code)
            FROM Course c 
            WHERE c.subCategory.code =:subcategoryCode
            """)
    Page<CourseResponse> findCoursesBySubCategory(String subcategoryCode, Pageable pageable);
}
