package com.br.levelup.DAO;

import com.br.levelup.model.Course;
import com.br.levelup.model.dto.CourseDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNull;
import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

public class CourseDAO {

    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Course course) throws SQLException {
        cantBeNull(course);
        connection.setAutoCommit(false);
        String sql = """
                INSERT INTO course (name, code, estimated_time_in_hours, visibility, target_audience, instructor_id, 
                resume, developed_skills, subcategory_id) VALUES(?, ?, ?, ?, ?, (SELECT id FROM instructor 
                WHERE name = ?), ?, ?, (SELECT id FROM subcategory WHERE code = ?))
                """;
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            cantBeNull(course);
            cantBeNull(stm);
            stm.setString(1, course.getName());
            stm.setString(2, course.getCode());
            stm.setInt(3, course.getEstimatedTimeInHours());
            stm.setBoolean(4, course.isVisibility());
            stm.setString(5, course.getTargetAudience());
            stm.setString(6, course.getInstructorName());
            stm.setString(7, course.getResume());
            stm.setString(8, course.getDevelopedSkills());
            stm.setString(9, course.getSubCategoryCode());

            stm.execute();

            ResultSet rst = stm.getGeneratedKeys();
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("EXECUTING ROLLBACK!");
            connection.rollback();
        }
    }

    public List<CourseDTO> listPublicCourses() {
        List<CourseDTO> courses = new ArrayList<>();

        String sql = """
                SELECT c.id, c.name, estimated_time_in_hours, s.id, s.name FROM course c
                INNER JOIN subcategory s ON s.id = c.subcategory_id
                WHERE visibility = true;
                """;
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.execute();
            try (ResultSet rst = stm.getResultSet()) {
                while (rst.next()) {
                    CourseDTO courseDTO = new CourseDTO(rst.getLong(1), rst.getString(2),
                            rst.getInt(3), rst.getLong(4), rst.getString(5));

                    courses.add(courseDTO);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return courses;
    }

    public void delete(String code) throws SQLException {
        cantBeNullOrEmpty(code);
        connection.setAutoCommit(false);
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM course WHERE code = ?")) {
            stm.setString(1, code);
            stm.execute();
            Integer deletedLines = stm.getUpdateCount();
            System.out.println("Modified lines: " + deletedLines);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void updatePublicVisibility() throws SQLException {
        connection.setAutoCommit(false);
        String sql = "UPDATE course SET visibility = true WHERE visibility = false;";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.execute();
            Integer updateLines = stm.getUpdateCount();
            System.out.println("Modified Lines: " + updateLines);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

}
