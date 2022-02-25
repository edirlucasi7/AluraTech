import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.SubCategory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.br.levelup.model.SubCategory.activeSubCategories;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeEndTagsInHtml;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeStartTagsInHtml;
import static com.br.levelup.service.CsvReaderService.*;
import static com.br.levelup.service.HtmlWriterService.writeHtmlCategory;
import static com.br.levelup.service.HtmlWriterService.writeHtmlSubCategory;

public class AluraTechCsv {

    public static void main(String[] args) throws IOException {

        List<Category> categories = csvReaderCategory("alurinha/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = csvReaderSubCategory(categories, "alurinha/planilha-dados-escola - Subcategoria.csv");
        List<Course> courses = csvReaderCourse(subCategories, "alurinha/planilha-dados-escola - Curso.csv");

        categories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        subCategories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        courses.forEach(System.out::println);

        try (PrintWriter ps = new PrintWriter("alurinha/categoria.html", "UTF-8");
             BufferedWriter bw = new BufferedWriter(ps)) {

                List<SubCategory> activeSubCategories = activeSubCategories(subCategories);

                writeStartTagsInHtml(bw);

                writeHtmlCategory(categories, courses, bw);

                writeHtmlSubCategory(activeSubCategories, courses, bw);

                writeEndTagsInHtml(bw);
        }
    }

}
