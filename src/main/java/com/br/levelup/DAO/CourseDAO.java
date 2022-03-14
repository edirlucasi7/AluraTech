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

    public void save(Course course) throws SQLException {
        cantBeNull(course);
        connection.setAutoCommit(false);
        String sql = "INSERT INTO course (name, code, estimated_time_in_hours, visibility, " +
                "target_audience, instructor_id, resume, developed_skills, subcategory_id) Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            addCourse(course, stm);

            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("EXECUTING ROLLBACK!");
            connection.rollback();
        }
    }

    public List<CourseDTO> listPublicCourses() throws SQLException {
        List<CourseDTO> courses = new ArrayList<>();
        connection.setAutoCommit(false);

        String sql = "SELECT id, name, estimated_time_in_hours, subcategory_id FROM course WHERE visibility = true;";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.execute();
            try(ResultSet rst = stm.getResultSet()) {
                while(rst.next()) {
                    PreparedStatement stm2 = connection.prepareStatement("SELECT name FROM subcategory WHERE id = ?");
                    String subcategoryName = findSubCategoryNameById(rst.getLong(4), stm2);
                    CourseDTO courseDTO = new CourseDTO(rst.getLong(1), rst.getString(2),
                            rst.getInt(3), rst.getLong(4), subcategoryName);

                    courses.add(courseDTO);
                }
            }
            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            connection.rollback();
        }
        return courses;
    }

    public void delete(String code) throws SQLException {
        cantBeNullOrEmpty(code);
        connection.setAutoCommit(false);
        try(PreparedStatement stm = connection.prepareStatement("DELETE FROM course WHERE code = ?")) {
            deleteCourse(code, stm);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void updatePublicVisibility() throws SQLException {
        connection.setAutoCommit(false);
        String sql = "UPDATE course SET visibility = true WHERE visibility = false;";
        try(PreparedStatement stm = connection.prepareStatement(sql)) {
            executeUpdatePublicVisibility(stm);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    private void executeUpdatePublicVisibility(PreparedStatement stm) throws SQLException {
        stm.execute();
        Integer updateLines = stm.getUpdateCount();
        System.out.println("Modified Line: " + updateLines);
    }

    private static void deleteCourse(String courseCode, PreparedStatement stm) throws SQLException {
        stm.setString(1, courseCode);
        stm.execute();
        Integer deletedLines = stm.getUpdateCount();
        System.out.println("Modified lines: " + deletedLines);
    }

    private static void addCourse(Course course, PreparedStatement stm) throws SQLException {
        stm.setString(1, course.getName());
        stm.setString(2, course.getCode());
        stm.setInt(3, course.getEstimatedTimeInHours());
        stm.setBoolean(4, course.isVisibility());
        stm.setString(5, course.getTargetAudience());
        stm.setInt(6, 1);
        stm.setString(7, course.getResume());
        stm.setString(8, course.getDevelopedSkills());
        stm.setInt(9, 1);

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
    }

    private static String findSubCategoryNameById(Long subCategoryId, PreparedStatement stm2) throws SQLException {
        stm2.setLong(1, subCategoryId);
        stm2.execute();

        ResultSet rst2 = stm2.getResultSet();
        while(rst2.next()) {
            String subcategory_name = rst2.getString("name");
            return subcategory_name;
        }
        return "Category Not found!";
    }

}
