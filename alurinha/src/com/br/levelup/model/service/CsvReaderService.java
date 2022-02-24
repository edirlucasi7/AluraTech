package com.br.levelup.model.service;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.br.levelup.model.SubCategory.processingOrder;
import static com.br.levelup.model.SubCategory.verifyDescriptionEmpty;
import static com.br.levelup.model.utils.ValidatorUtils.cantBeNull;
import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

public class CsvReaderService {

    private static Scanner csvReader(String filePath) throws IOException {
        cantBeNullOrEmpty(filePath);
        return new Scanner(new File(filePath));
    }

    public static List<Category> csvReaderCategory(String file) throws IOException {
        cantBeNullOrEmpty(file);

        Scanner scannerCategory = csvReader(file);

        List<Category> categories = new ArrayList<>();

        scannerCategory.nextLine();
        while(scannerCategory.hasNextLine()) {
            String line = scannerCategory.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            String name = lineScanner.next().trim();
            String code = lineScanner.next().trim();
            Integer order = Category.processingOrder(lineScanner.next());
            String description = lineScanner.next().trim();
            boolean active = Category.convertToBoolean(lineScanner.next());
            String imageUrl = lineScanner.next().trim();
            String colorCode = lineScanner.next().trim();

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
            Optional<Category> possibleCategory = findCategoryByCode(categories, lineScanner.next());
            if(possibleCategory.isPresent()) {
                SubCategory newSubCategory = new SubCategory(name, code, possibleCategory.get());

                if(!order.equals(0)) {
                    newSubCategory.setOrder(order);
                }
                if(!description.isEmpty()) {
                    newSubCategory.setShortDescription(description);
                }

                newSubCategory.setActive(active);
                subCategories.add(newSubCategory);
            }

            lineScanner.close();
        }
        return subCategories;
    }

    public static List<Course> csvReaderCourse(List<SubCategory> subCategories, String file) throws IOException {
        cantBeNull(subCategories);
        cantBeNullOrEmpty(file);

        Scanner scannerCourse = csvReader(file);

        List<Course> courses = new ArrayList<>();

        scannerCourse.nextLine();
        while(scannerCourse.hasNextLine()) {
            String line = scannerCourse.nextLine();

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            String name = lineScanner.next().trim();
            String code = lineScanner.next().trim();
            Integer estimatedTimeInHours = lineScanner.nextInt();
            boolean visibility = Course.convertToBoolean(lineScanner.next());
            String targetAudience = lineScanner.next().trim();
            Instructor instructor = new Instructor(lineScanner.next().trim());
            String resume = lineScanner.next().trim();
            String developedSkills = Course.verifyDevelopedSkillsEmpty(lineScanner.next().trim());

            if(!scannerCourse.hasNext()) {
                System.out.println("Can't read this line: " + line + "from csv. The object subCategory should be not null!\n");
                break;
            }

            Optional<SubCategory> possibleSubCategory = findSubCategoryByCode(subCategories, lineScanner.next());
            if(possibleSubCategory.isPresent()) {
                Course newCourse = new Course(name, code, estimatedTimeInHours, instructor, possibleSubCategory.get());
                newCourse.setVisibility(visibility);
                newCourse.setTargetAudience(targetAudience);
                newCourse.setResume(resume);
                newCourse.setDevelopedSkills(developedSkills);
                courses.add(newCourse);
            }

            lineScanner.close();
        }
        return courses;
    }

    private static Optional<Category> findCategoryByCode(List<Category> categories, String categoryCode) {
        return categories.stream()
                .filter(category -> category.getCode().equalsIgnoreCase(categoryCode))
                .findFirst();
    }

    private static Optional<SubCategory> findSubCategoryByCode(List<SubCategory> subCategories, String subCategoryCode) {
        return subCategories.stream()
                .filter(subCategory -> subCategory.getCode().equalsIgnoreCase(subCategoryCode))
                .findFirst();
    }

}
