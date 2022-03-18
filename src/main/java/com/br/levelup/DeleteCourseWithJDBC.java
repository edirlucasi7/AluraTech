package com.br.levelup;

import com.br.levelup.dao.CourseDAO;
import com.br.levelup.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteCourseWithJDBC {

    public static void main(String[] args) throws SQLException {
        try(Connection connection = new ConnectionFactory().recoverConnection()) {
            CourseDAO courseDAO = new CourseDAO(connection);
            courseDAO.delete("java-design");
        }
    }

}
