package com.br.levelup;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.SubCategory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import static com.br.levelup.model.Course.instructorNamesAndCourses;
import static com.br.levelup.model.SubCategory.activeSubCategoriesSortedByOrder;
import static com.br.levelup.model.SubCategory.totalOfActiveSubCategoriesWithDescription;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeEndTagsInHtml;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeStartTagsInHtml;
import static com.br.levelup.service.CsvReaderService.*;
import static com.br.levelup.service.HtmlWriterService.writeHtmlCategory;
import static com.br.levelup.service.HtmlWriterService.writeHtmlSubCategory;
import static com.br.levelup.service.WriteDataScript.*;

public class AluraTechCsv {

    public static void main(String[] args) throws IOException {

        List<Category> categories = readCategories("planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = readSubCategories(categories, "planilha-dados-escola - Subcategoria.csv");
        List<Course> courses = readCourses(subCategories, "planilha-dados-escola - Curso.csv");

        categories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        subCategories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        courses.forEach(System.out::println);

        try (PrintWriter ps = new PrintWriter("categoria.html", "UTF-8");
             BufferedWriter bw = new BufferedWriter(ps)) {

                List<SubCategory> activeSubCategories = activeSubCategoriesSortedByOrder(subCategories);

                writeStartTagsInHtml(bw);

                writeHtmlCategory(categories, courses, bw);

                writeHtmlSubCategory(activeSubCategories, courses, bw);

                writeEndTagsInHtml(bw);
        }

        System.out.println("------------------------------Active Categories--------------------------------------------");
        List<Category> activeCategories = Category.activeCategories(categories);
        activeCategories.forEach(System.out::println);

        System.out.println();

        System.out.println("------------------------------SubCategories Without Description-----------------------------");
        List<SubCategory> onlyWithoutDescritpion = SubCategory.subCategoriesWithoutDescription(subCategories);
        onlyWithoutDescritpion.forEach(System.out::println);

        System.out.println("------------------------------Exists Private Course-----------------------------");
        boolean existsPrivateCourses = Course.existsPrivate(courses);
        System.out.println(existsPrivateCourses);

        System.out.println("------------------------------Course Instructors Names-----------------------------");
        System.out.println(Course.instructorsNames(courses));

        System.out.println("------------------------------Active SubCategories With Description-----------------------------");
        System.out.println(totalOfActiveSubCategoriesWithDescription(subCategories));

        System.out.println("------------------------------Instructor Names And Courses-----------------------------");
        System.out.println(instructorNamesAndCourses(courses));

        try(PrintStream ps = new PrintStream("src/main/resources/script.sql")) {

            loadCategoryData(ps, "planilha-dados-escola - Categoria.csv");
            loadSubCategoryData(ps, "planilha-dados-escola - Categoria.csv", "planilha-dados-escola - Subcategoria.csv");
            loadCourseData(ps, "planilha-dados-escola - Categoria.csv", "planilha-dados-escola - Subcategoria.csv",
                    "planilha-dados-escola - Curso.csv");

        }

    }

}
