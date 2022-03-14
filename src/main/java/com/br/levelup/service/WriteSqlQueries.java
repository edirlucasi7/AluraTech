package com.br.levelup.service;

import java.io.IOException;
import java.io.PrintStream;

public class WriteSqlQueries {

    public static void getDataFromActiveCategories(PrintStream ps) throws IOException {
            String sqlTemplate = """
                    SELECT * FROM category WHERE active = true ORDER BY order_visualization;               
                    """;
            ps.println(sqlTemplate);
    }

    public static void getDataFromActiveSubCategories(PrintStream ps) throws IOException {
        String sqlTemplate = """
                    SELECT * FROM subcategory WHERE active = true ORDER BY order_visualization;    
                    """;
        ps.println(sqlTemplate);
    }

    public static void getDataFromPublicCourses(PrintStream ps) throws IOException {
        String sqlTemplate = """
                    SELECT * FROM course WHERE visibility = true;               
                    """;
        ps.println(sqlTemplate);
    }

    public static void getNamesFromSubCategoriesWithoutDescription(PrintStream ps) throws IOException {
        String sqlTemplate = """
                    SELECT name FROM subcategory WHERE short_description IS NULL OR short_description = '';               
                    """;
        ps.println(sqlTemplate);
    }

}
