package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT DISTINCT co FROM Course co " +
            "INNER JOIN SubCategory su ON su.id = co.subCategory.id " +
            "INNER JOIN Category c ON su.category.id = :idCategory " +
            "WHERE co.visibility = true")
    List<Course> findPublicCoursesByCategoryId(Long idCategory);
}
