package com.br.levelup.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import static com.br.levelup.model.Course.namesOfCoursesFromSubCategory;
import static com.br.levelup.model.utils.CsvReaderUtils.csvReader;
import static com.br.levelup.model.utils.ValidatorUtils.*;

public class SubCategory {

    private String name;
    private String code;
    private String shortDescription;
    private String studyGuide;
    private boolean active;
    private Integer order;
    private Category category;

    public SubCategory(String name, String code, Category category) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code should not be empty!");
        cantBeNull(category, "The object category should not be null!");
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public void setShortDescription(String shortDescription) {
        cantBeNullOrEmpty(shortDescription, "The field shortDescription should not be empty!");
        this.shortDescription = shortDescription;
    }

    public void setStudyGuide(String studyGuide) {
        cantBeNullOrEmpty(studyGuide, "The field studyGuide should not be empty!");
        this.studyGuide = studyGuide;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        cantBeLessZero(order, "The field order should not be null or less than zero!");
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public Category getCategory() {
        return this.category;
    }

    public Integer getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isActive() {
        return active;
    }

    public static String verifyDescriptionEmpty(String description) {
        return description.equals("") ? "Uninformed description" : description;
    }

    public static SubCategory findSubCategoryByCode(List<SubCategory> subCategories, String subCategoryCode) {
        return subCategories.stream()
                .filter(subCategory -> subCategory.getCode().equalsIgnoreCase(subCategoryCode))
                .findFirst().orElse(null);
    }

    public static Integer processingOrder(String stringOrder) {
        return stringOrder.equals("") ? 0 : Integer.parseInt(stringOrder);
    }

    public static boolean convertToBoolean(String stringActive) {
        return stringActive.equals("ATIVA");
    }

    public static List<SubCategory> csvReaderSubCategory(List<Category> categories, String file) throws IOException {
        cantBeNull(categories);
        cantBeNullOrEmpty(file);

        Scanner scannerSubCategory = csvReader(file);

        List<SubCategory> subCategories = new ArrayList<>();

        scannerSubCategory.nextLine();
        while(scannerSubCategory.hasNextLine()) {
            String line = scannerSubCategory.nextLine();

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            String name = lineScanner.next();
            String code = lineScanner.next();
            Integer order = processingOrder(lineScanner.next());
            String description = verifyDescriptionEmpty(lineScanner.next());


            boolean active = SubCategory.convertToBoolean(lineScanner.next());
            Category category = Category.findCategoryByCode(categories, lineScanner.next());

            SubCategory newSubCategory = new SubCategory(name, code, category);

            if(!order.equals(0)) {
                newSubCategory.setOrder(order);
            }
            if(!description.isEmpty()) {
                newSubCategory.setShortDescription(description);
            }

            newSubCategory.setActive(active);

            subCategories.add(newSubCategory);

            lineScanner.close();
        }
        return subCategories;
    }

    public static String writeStartingHeader() {
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
        return startingHeaderSubCategory;
    }

    public static void writeIteratorSubCategory(List<SubCategory> subCategories, List<Course> courses, BufferedWriter bw) throws IOException {
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
    }

    public static String writeClosingHeader() {
        String closingHeaderSubCategory = """
                             </table>
                """;
        return closingHeaderSubCategory;
    }

    public static List<SubCategory> activeSubCategorias(List<SubCategory> subCategories) {
        return subCategories.stream().filter(SubCategory::isActive)
                .sorted(Comparator.comparing(SubCategory::getOrder)).toList();
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", indication=" + active +
                ", order=" + order +
                ", category=" + category +
                '}';
    }

}
