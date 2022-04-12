package br.com.levelup.aluratech.repository;

import br.com.levelup.aluratech.controller.projection.report.ReportInstructorWithMoreCourses;
import br.com.levelup.aluratech.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query(value = """
            SELECT i.name, COUNT(*) AS amount
            FROM instructor i INNER JOIN course c
            ON i.id = c.instructor_id
            GROUP BY name ORDER BY amount DESC LIMIT 1
            """, nativeQuery = true)
    List<ReportInstructorWithMoreCourses> findInstructorWithMoreCourses();

}
