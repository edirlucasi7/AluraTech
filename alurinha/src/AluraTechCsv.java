import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.SubCategory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.br.levelup.model.SubCategory.activeSubCategorias;
import static com.br.levelup.model.service.CsvReaderService.*;
import static com.br.levelup.model.service.HtmlWriterService.writeHtmlCategory;
import static com.br.levelup.model.service.HtmlWriterService.writeHtmlSubCategory;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeEndTagsInHtml;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeStartTagsInHtml;

public class AluraTechCsv {

    public static void main(String[] args) throws IOException {

        List<Category> categories = csvReaderCategory("planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = csvReaderSubCategory(categories, "planilha-dados-escola - Subcategoria.csv");
        List<Course> courses = csvReaderCourse(subCategories, "planilha-dados-escola - Curso.csv");

        categories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        subCategories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        courses.forEach(System.out::println);

        try (PrintWriter ps = new PrintWriter("categoria.html", "UTF-8");
             BufferedWriter bw = new BufferedWriter(ps)) {

            List<SubCategory> activeSubCategories = activeSubCategorias(subCategories);

            writeStartTagsInHtml(bw);

            writeHtmlCategory(categories, courses, bw);

            writeHtmlSubCategory(activeSubCategories, courses, bw);

            writeEndTagsInHtml(bw);
        }

    }

}
