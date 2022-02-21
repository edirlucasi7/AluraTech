import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.SubCategory;

import java.io.*;
import java.util.List;

import static com.br.levelup.model.Category.csvReaderCategory;
import static com.br.levelup.model.Course.csvReaderCourse;
import static com.br.levelup.model.SubCategory.csvReaderSubCategory;

public class AluraTechCsv {

    public static void main(String[] args) throws IOException {

        List<Category> categories = csvReaderCategory("/home/icety/Documentos/csvs/planilha-dados-escola - Categoria.csv");
        List<SubCategory> subCategories = csvReaderSubCategory(categories, "/home/icety/Documentos/csvs/planilha-dados-escola - Subcategoria.csv");
        List<Course> courses = csvReaderCourse(subCategories, "/home/icety/Documentos/csvs/planilha-dados-escola - Curso.csv");

        categories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        subCategories.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------");
        courses.forEach(System.out::println);

    }

}
