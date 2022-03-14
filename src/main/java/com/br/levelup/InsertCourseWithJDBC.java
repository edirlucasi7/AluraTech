package com.br.levelup;

import com.br.levelup.DAO.CourseDAO;
import com.br.levelup.db.ConnectionFactory;
import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;

import java.sql.*;

public class InsertCourseWithJDBC {

    public static void main(String[] args) throws SQLException {

        Instructor instructor = new Instructor("Thais");
        Category category = new Category("Design", "design");
        SubCategory subCategory = new SubCategory("UX", "css", category);
        Course course = new Course("Curso Java para Data Science: primeiros passos", "java-design",
                10, instructor, subCategory);
        course.setVisibility(false);
        course.setTargetAudience("Devs");
        course.setResume("Aprendendo design de código com java");
        course.setDevelopedSkills("várias habilidades");

        try(Connection connection = new ConnectionFactory().recoverConnection()) {
            CourseDAO courseDAO = new CourseDAO(connection);
            courseDAO.save(course);
            System.out.println("Resource created: "+ course);
        }

    }

}
