package com.br.levelup.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.br.levelup.model.utils.CsvReaderUtils.csvReader;
import static com.br.levelup.model.utils.ValidatorUtils.*;

public class Category {

    private String name;
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    private Integer order;
    private String imageUrl;
    private String colorCode;

    public Category(String name, String code) {
        cantBeNullOrEmpty(name, "The field name should not be null or empty!");
        cantBeNullOrEmpty(code, "The field code should not be null or empty!");
        containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setShortDescription(String shortDescription) {
        cantBeNullOrEmpty(shortDescription, "The field shortDescription should not be null or empty!");
        this.shortDescription = shortDescription;
    }

    public void setStudyGuide(String studyGuide) {
        cantBeNullOrEmpty(studyGuide, "The field studyGuide should not be null or empty!");
        this.studyGuide = studyGuide;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        cantBeLessZero(order, "The field order should not be less than zero!");
        this.order = order;
    }

    public void setImageUrl(String imageUrl) {
        cantBeNullOrEmpty(imageUrl, "The field imageUrl should not be empty!");
        this.imageUrl = imageUrl;
    }

    public void setColorCode(String colorCode) {
        isHexadecimal(colorCode, "The field codeColor should not be out of hexadecimal format!");
        this.colorCode = colorCode;
    }

    public static boolean convertToBoolean(String stringActive) {
        return stringActive.equals("ATIVA");
    }

    public static Integer processingOrder(String stringOrder) {
        return stringOrder.equals("") ? 0 : Integer.parseInt(stringOrder);
    }

    public static Category findCategoryByCode(List<Category> categories, String categoryCode) {
        return categories.stream()
                .filter(category -> category.getCode().equalsIgnoreCase(categoryCode))
                .findFirst().orElse(null);
    }

    public static long numberOfCoursesFromCategory(List<Course> courses, String categoryCode) {
        return courses.stream()
                .filter(course -> course.getSubCategory().getCategory().getCode().equals(categoryCode)).count();
    }

    public static int sumOfEstimatedTimeInHoursFromCoursesFromCategory(List<Course> courses, String categoryCode) {
        List<Course> coursesFromSubCategory = courses.stream()
                .filter(course -> course.getSubCategory().getCategory().getCode().equals(categoryCode))
                .collect(Collectors.toList());

        return coursesFromSubCategory.stream().mapToInt(Course::getEstimatedTimeInHours).sum();
    }

    public static String writeStartingHeader() {
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
                                      <th scope="col">Soma Total de Horas de Curso</th>
                                    </tr>
                                </thead>
                """;
        return startingHeaderCategory;
    }

    public static void writeIteratorCategory(List<Category> categories, List<Course> courses, BufferedWriter bw) throws IOException {
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
                    numberOfCoursesFromCategory(courses, nextCategory.getCode()),
                    sumOfEstimatedTimeInHoursFromCoursesFromCategory(courses, nextCategory.getCode()));
            bw.write(bodyContentCategory);
        }
    }

    public static String writeClosingHeader() {
        String closingHeaderCategory = """
                             </table>
                """;
        return closingHeaderCategory;
    }

    public static List<Category> csvReaderCategory(String file) throws IOException {
        Scanner scannerCategory = csvReader(file);

        List<Category> categories = new ArrayList<>();

        scannerCategory.nextLine();
        while(scannerCategory.hasNextLine()) {
            String line = scannerCategory.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            String name = lineScanner.next();
            String code = lineScanner.next();
            Integer order = Category.processingOrder(lineScanner.next());
            String description = lineScanner.next();
            boolean active = Category.convertToBoolean(lineScanner.next());
            String imageUrl = lineScanner.next();
            String colorCode = lineScanner.next();

            Category newCategory = new Category(name, code);

            if(!order.equals(0)) {
                newCategory.setOrder(order);
            }

            newCategory.setShortDescription(description);
            newCategory.setActive(active);
            newCategory.setImageUrl(imageUrl);
            newCategory.setColorCode(colorCode);

            categories.add(newCategory);

            lineScanner.close();
        }
        return categories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", imageUrl='" + imageUrl + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }

}
