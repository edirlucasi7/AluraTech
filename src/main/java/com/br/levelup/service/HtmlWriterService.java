package com.br.levelup.service;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.SubCategory;
import com.br.levelup.model.dto.CategoryDTO;
import com.br.levelup.model.dto.PublicCourseDTO;
import com.br.levelup.model.dto.SubCategoryDTO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlWriterService {

    public static void writeHtmlCategory(List<Category> categories, List<Course> courses, BufferedWriter bw) throws IOException {
        String startingHeaderCategory = """
                             <h3>Categorias:</h3>
                             <table class="table table-striped">
                                <thead>
                                    <tr>
                                      <th scope="col">Nome</th>
                                      <th scope="col">Descrição</th>
                                      <th scope="col">Ícone</th>
                                      <th scope="col">Cor</th>
                                      <th scope="col">Número Total de Cursos</th>
                                      <th scope="col">Soma Total de Horas de Cursos</th>
                                    </tr>
                                </thead>
                """;
        bw.write(startingHeaderCategory);

        Iterator<Category> iteratorCategory = categories.iterator();
        while(iteratorCategory.hasNext()) {
            Category nextCategory = iteratorCategory.next();
            String bodyContentCategory = """
                                    <tbody>
                                        <tr>
                                          <td>%s</td>
                                          <td>%s</td>
                                          <td>
                                            <img src="%s" width="50px"></img>
                                          </td>
                                          <td>%s</td>
                                          <td>%d</td>
                                          <td>%d</td>
                                        </tr>
                                    </tbody>
                    """.formatted(nextCategory.getName(), nextCategory.getShortDescription(),
                    nextCategory.getImageUrl(), nextCategory.getColorCode(),
                    numberOfCoursesByCategory(courses, nextCategory.getCode()),
                    sumOfEstimatedTimeInHoursFromCourses(courses, nextCategory.getCode()));
            bw.write(bodyContentCategory);
        }

        String closingHeaderCategory = """
                             </table>
                """;
        bw.write(closingHeaderCategory);
    }

    public static void writeHtmlSubCategory(List<SubCategory> subCategories, List<Course> courses, BufferedWriter bw) throws IOException {
        String startingHeaderSubCategory = """
                             <h4>SubCategorias:</h4>
                             <table class="table table-striped">
                                <thead>
                                    <tr>
                                      <th scope="col">Nome</th>
                                      <th scope="col">Descrição</th>
                                      <th scope="col">Cursos</th>
                                    </tr>
                                </thead>
                """;
        bw.write(startingHeaderSubCategory);

        Iterator<SubCategory> iteratorSubCategory = subCategories.iterator();
        while(iteratorSubCategory.hasNext()) {
            SubCategory nextSubCategory = iteratorSubCategory.next();
            String bodyContentSubCategory = """
                                    <tbody>
                                       <tr>
                                         <td>%s</td>
                                         <td>%s</td>
                                         <td>%s</td>
                                       </tr>
                                    </tbody>        
                    """.formatted(nextSubCategory.getName(), nextSubCategory.getShortDescription(),
                    namesOfCoursesFromSubCategory(courses, nextSubCategory.getCode()));
            bw.write(bodyContentSubCategory);
        }

        String closingHeaderSubCategory = """
                             </table>
                """;
        bw.write(closingHeaderSubCategory);
    }

    public static void writeHtmlPublicCourses(List<PublicCourseDTO> publicCourses, BufferedWriter bw) throws IOException {
        String startingHeaderPublicCourses = """
                             <h4>Cursos Públicos:</h4>
                             <table class="table table-striped">
                                <thead>
                                    <tr>
                                      <th scope="col">Nome: </th>
                                      <th scope="col">Tempo de finalização: </th>
                                      <th scope="col">Identificador da SubCategoria: </th>
                                      <th scope="col">Nome da SubCategoria: </th>
                                    </tr>
                                </thead>
                """;
        bw.write(startingHeaderPublicCourses);

        Iterator<PublicCourseDTO> iteratorPublicCourses = publicCourses.iterator();
        while(iteratorPublicCourses.hasNext()) {
            PublicCourseDTO nextPublicCourse = iteratorPublicCourses.next();
            String bodyContentPublicCourses = """
                                    <tbody>
                                       <tr>
                                         <td>%s</td>
                                         <td>%d</td>
                                         <td>%d</td>
                                         <td>%s</td>
                                       </tr>
                                    </tbody>        
                    """.formatted(nextPublicCourse.getName(), nextPublicCourse.getEstimatedTimeInHours(), nextPublicCourse.getSubCategoryId(),
                    nextPublicCourse.getSubCategoryName());
            bw.write(bodyContentPublicCourses);
        }

        String closingHeaderPublicCourses = """
                             </table>
                """;
        bw.write(closingHeaderPublicCourses);
    }

    public static void writeHtmlActiveCategories(List<CategoryDTO> categoryDTOS, BufferedWriter bw) throws IOException {
        String startingHeaderActiveCategories = """
                             <h4>Categorias Ativas:</h4>
                             <table class="table table-striped">
                                <thead>
                                    <tr>
                                      <th scope="col">Nome: </th>
                                      <th scope="col">Código: </th>
                                      <th scope="col">Descrição: </th>
                                      <th scope="col">Visibilidade: </th>
                                      <th scope="col">Ordem: </th>
                                      <th scope="col">Url da imagem: </th>
                                      <th scope="col">Código da Cor: </th>
                                    </tr>
                                </thead>
                """;
        bw.write(startingHeaderActiveCategories);

        Iterator<CategoryDTO> iteratorCategoryDTO = categoryDTOS.iterator();
        while(iteratorCategoryDTO.hasNext()) {
            CategoryDTO nextCategoryDTO = iteratorCategoryDTO.next();
            String bodyContentPublicCourses = """
                                    <tbody>
                                       <tr>
                                         <td>%s</td>
                                         <td>%s</td>
                                         <td>%s</td>
                                         <td>%b</td>
                                         <td>%d</td>
                                         <td>%s</td>
                                         <td>%s</td>
                                       </tr>
                                    </tbody>        
                    """.formatted(nextCategoryDTO.getName(), nextCategoryDTO.getCode(),
                    nextCategoryDTO.getShortDescription(), nextCategoryDTO.isActive(), nextCategoryDTO.getOrder(),
                    nextCategoryDTO.getImageUrl(), nextCategoryDTO.getColorCode());
            bw.write(bodyContentPublicCourses);
        }

        String closingHeaderActiveCategories = """
                             </table>
                """;
        bw.write(closingHeaderActiveCategories);
    }

    public static void writeHtmlActiveSubCategories(List<SubCategoryDTO> subCategoryDTOS, BufferedWriter bw) throws IOException {
        String startingHeaderActiveSubCategories = """
                             <h4>SubCategorias Ativas:</h4>
                             <table class="table table-striped">
                                <thead>
                                    <tr>
                                      <th scope="col">Nome: </th>
                                      <th scope="col">Código: </th>
                                      <th scope="col">Descrição: </th>
                                      <th scope="col">Visibilidade: </th>
                                      <th scope="col">Ordem: </th>
                                      <th scope="col">Identificador da Categoria: </th>
                                    </tr>
                                </thead>
                """;
        bw.write(startingHeaderActiveSubCategories);

        Iterator<SubCategoryDTO> iteratorSubCategoryDTO = subCategoryDTOS.iterator();
        while(iteratorSubCategoryDTO.hasNext()) {
            SubCategoryDTO nextSubCategoryDTO = iteratorSubCategoryDTO.next();
            String bodyContentActiveSubCategories = """
                                    <tbody>
                                       <tr>
                                         <td>%s</td>
                                         <td>%s</td>
                                         <td>%s</td>
                                         <td>%b</td>
                                         <td>%d</td>
                                         <td>%d</td>
                                       </tr>
                                    </tbody>        
                    """.formatted(nextSubCategoryDTO.getName(), nextSubCategoryDTO.getCode(), nextSubCategoryDTO.getShortDescription(),
                    nextSubCategoryDTO.isActive(), nextSubCategoryDTO.getOrder(), nextSubCategoryDTO.getIdCategory());
            bw.write(bodyContentActiveSubCategories);
        }

        String closingHeaderActiveCategories = """
                             </table>
                """;
        bw.write(closingHeaderActiveCategories);
    }

    public static void writeHtmlSubCategoriesWithoutDescription(List<String> subCategoryDTOS, BufferedWriter bw) throws IOException {
        String startingHeaderActiveSubCategories = """
                             <h4>SubCategorias Sem Descrição:</h4>
                             <table class="table table-striped">
                                <thead>
                                    <tr>
                                      <th scope="col">Nome: </th>
                                    </tr>
                                </thead>
                """;
        bw.write(startingHeaderActiveSubCategories);

        Iterator<String> iteratorSubCategoryDTO = subCategoryDTOS.iterator();
        while(iteratorSubCategoryDTO.hasNext()) {
            String nextSubCategoryDTO = iteratorSubCategoryDTO.next();
            String bodyContentActiveSubCategories = """
                                    <tbody>
                                       <tr>
                                         <td>%s</td>
                                       </tr>
                                    </tbody>        
                    """.formatted(nextSubCategoryDTO);
            bw.write(bodyContentActiveSubCategories);
        }

        String closingHeaderActiveCategories = """
                             </table>
                """;
        bw.write(closingHeaderActiveCategories);
    }

    private static long numberOfCoursesByCategory(List<Course> courses, String categoryCode) {
        return courses.stream()
                .filter(course -> course.getCategoryCode().equals(categoryCode)).count();
    }

    private static int sumOfEstimatedTimeInHoursFromCourses(List<Course> courses, String categoryCode) {
        return courses.stream()
                .filter(course -> course.getCategoryCode().equals(categoryCode)).mapToInt(Course::getEstimatedTimeInHours).sum();
    }

    private static String namesOfCoursesFromSubCategory(List<Course> courses, String subCategoryCode) {
        List<Course> subCategoryCourses = courses.stream()
                .filter(course -> course.getSubCategory().getCode().equals(subCategoryCode)).collect(Collectors.toList());

        StringBuilder coursesNames = new StringBuilder();
        for (Course c : subCategoryCourses) {
            coursesNames.append(c.getName()).append(",");
            coursesNames.deleteCharAt(coursesNames.length()-1);
        }
        return coursesNames.toString();
    }

}
