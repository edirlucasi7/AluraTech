package com.br.levelup;

import com.br.levelup.DAO.CourseDAO;
import com.br.levelup.db.ConnectionFactory;
import com.br.levelup.model.dto.CourseDTO;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.br.levelup.model.utils.WriteHtmlUtils.writeEndTagsInHtml;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeStartTagsInHtml;
import static com.br.levelup.service.HtmlWriterService.writeHtmlPublicCourses;

public class SelectCourseWithJDBC {

    public static void main(String[] args) throws SQLException, IOException {
        try(Connection connection = new ConnectionFactory().recoverConnection();
            PrintWriter ps = new PrintWriter("cursos-publicos.html", "UTF-8");
            BufferedWriter bw = new BufferedWriter(ps)) {

            CourseDAO courseDAO = new CourseDAO(connection);
            List<CourseDTO> courses = courseDAO.listPublicCourses();
            System.out.println("Courses: "+ courses);

            writeStartTagsInHtml(bw);
            writeHtmlPublicCourses(courses, bw);
            writeEndTagsInHtml(bw);
        }
    }

}
