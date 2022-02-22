import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.SubCategory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.br.levelup.model.Category.*;
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

        PrintWriter ps = new PrintWriter("categoria.html", "UTF-8");
        BufferedWriter bw = new BufferedWriter(ps);

        Map<String, Category> categoriesMap = categories.stream().collect(Collectors.toMap(Category::getCode, Function.identity()));

        String benningOfTheBody = """
            <html>
                <head>
                <head>
                <body>
                    <div class="container"> 
                    <h1>Categorias: </h1>
                """;
        bw.write(benningOfTheBody);

        long totalOfCoursesFromCategoryProgramacao = numberOfCoursesFromCategory(subCategories, courses, "programacao");
        int totalOfHoursFromCoursesFromCategoryProgramacao = sumOfEstimatedTimeInHoursFromCoursesFromCategory(subCategories, courses, "programacao");

        String middleOfBody1 = """
                            <div class="row" style="background-color:powderblue;">
                                <div class="col-6">  
                                    <p>Nome: %s</p>
                                    <p>Código: %s</p>
                                    <p>Ordem: %s</p>
                                    <p>Descrição: %s</p>
                                </div>
                                <div class="col-6">
                                    <p>Status: %s</p>
                                    <p>Icone: %s</p>
                                    <p class=col-md style="background-color:#00c86f;">Cor: %s</p>
                                    <p>Total de cursos da categoria: %d</p>
                                    <p>Soma total de horas estimadas dos cursos da categoria: %d</p>
                                </div>
                            </div>
                """.formatted(categoriesMap.get("programacao").getName(), categoriesMap.get("programacao").getCode(),
                categoriesMap.get("programacao").getOrder(), categoriesMap.get("programacao").getShortDescription(),
                categoriesMap.get("programacao").getActive(), categoriesMap.get("programacao").getImageUrl(),
                categoriesMap.get("programacao").getColorCode(), totalOfCoursesFromCategoryProgramacao, totalOfHoursFromCoursesFromCategoryProgramacao);
        bw.write(middleOfBody1);

        long totalOfCoursesFromCategoryDevops = numberOfCoursesFromCategory(subCategories, courses, "devops");
        int totalOfHoursFromCoursesFromCategoryDevops = sumOfEstimatedTimeInHoursFromCoursesFromCategory(subCategories, courses, "devops");

        String middleOfBody2 = """
                            <div class="row" style="background-color:powderblue;">
                                <div class="col-6">  
                                    <p>Nome: %s</p>
                                    <p>Código: %s</p>
                                    <p>Ordem: %s</p>
                                    <p>Descrição: %s</p>
                                </div>
                                <div class="col-6">
                                    <p>Status: %s</p>
                                    <p>Icone: %s</p>
                                    <p class=col-md style="background-color:#f16165;">Cor: %s</p>
                                    <p>Total de cursos da categoria: %d</p>
                                    <p>Soma total de horas estimadas dos cursos da categoria: %d</p>
                                </div>
                            </div>
                """.formatted(categoriesMap.get("devops").getName(), categoriesMap.get("devops").getCode(),
                categoriesMap.get("devops").getOrder(), categoriesMap.get("devops").getShortDescription(),
                categoriesMap.get("devops").getActive(), categoriesMap.get("devops").getImageUrl(),
                categoriesMap.get("devops").getColorCode(), totalOfCoursesFromCategoryDevops, totalOfHoursFromCoursesFromCategoryDevops);
        bw.write(middleOfBody2);

        long totalOfCoursesFromCategoryBusiness = numberOfCoursesFromCategory(subCategories, courses, "business");
        int totalOfHoursFromCoursesFromCategoryBusiness = sumOfEstimatedTimeInHoursFromCoursesFromCategory(subCategories, courses, "business");

        String middleOfBody3 = """
                            <div class="row" style="background-color:powderblue;">
                                <div class="col-6">  
                                    <p>Nome: %s</p>
                                    <p>Código: %s</p>
                                    <p>Ordem: %s</p>
                                    <p>Descrição: %s</p>
                                </div>
                                <div class="col-6">
                                    <p>Status: %s</p>
                                    <p>Ícone: %s</p>
                                    <p class=col-md style="background-color:#ff8c2a;">Cor: %s</p>
                                    <p>Total de cursos da categoria: %d</p>
                                    <p>Soma total de horas estimadas dos cursos da categoria: %d</p>
                                </div>
                            </div>
                """.formatted(categoriesMap.get("business").getName(), categoriesMap.get("business").getCode(),
                categoriesMap.get("business").getOrder(), categoriesMap.get("business").getShortDescription(),
                categoriesMap.get("business").getActive(), categoriesMap.get("business").getImageUrl(),
                categoriesMap.get("business").getColorCode(), totalOfCoursesFromCategoryBusiness, totalOfHoursFromCoursesFromCategoryBusiness);
        bw.write(middleOfBody3);

        String endOfBody = """
                    </div>
                <body>
            <html>
            """;
        bw.write(endOfBody);

        ps.flush();
        bw.flush();
        ps.close();
        bw.close();

    }

}
