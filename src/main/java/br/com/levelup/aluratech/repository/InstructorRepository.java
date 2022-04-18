package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.instructor.ExistingInstructorsProjection;
import br.com.levelup.aluratech.controller.projection.report.ReportInstructorWithMoreCoursesProjection;
import br.com.levelup.aluratech.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query(value = """
            SELECT i.name, COUNT(c.id) AS amount
            FROM instructor i INNER JOIN course c
            ON i.id = c.instructor_id
            GROUP BY name ORDER BY amount DESC LIMIT 1
            """, nativeQuery = true)
    Optional<ReportInstructorWithMoreCoursesProjection> findInstructorWithMoreCourses();

    @Query(value = """
            SELECT i.id, i.name FROM instructor i ORDER BY i.name ASC
            """, nativeQuery = true)
    List<ExistingInstructorsProjection> findInstructorsAlphabeticOrder();
}
