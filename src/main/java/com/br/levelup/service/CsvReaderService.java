package com.br.levelup.service;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;
import com.br.levelup.model.utils.ValidatorUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CsvReaderService {

    public static List<Category> readCategories(String file) throws IOException {
        ValidatorUtils.cantBeNullOrEmpty(file);

        List<Category> categories = new ArrayList<>();

        try (Scanner scannerCategory = csvReader(file)) {
            scannerCategory.nextLine();
            while (scannerCategory.hasNextLine()) {
                String line = scannerCategory.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                Category newCategory = buildCategory(lineScanner);

                categories.add(newCategory);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Category file or directory not found!");
        }
        return categories;
    }

    public static List<SubCategory> readSubCategories(List<Category> categories, String file) throws IOException {
        ValidatorUtils.cantBeNull(categories);
        ValidatorUtils.cantBeNullOrEmpty(file);

        List<SubCategory> subCategories = new ArrayList<>();

        try (Scanner scannerSubCategory = csvReader(file)) {

            scannerSubCategory.nextLine();
            while (scannerSubCategory.hasNextLine()) {
                String line = scannerSubCategory.nextLine();

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                SubCategory newSubCategory = buildSubCategory(categories, lineScanner);
                subCategories.add(newSubCategory);

            }
        } catch (FileNotFoundException ex) {
            System.out.println("SubCategory file or directory not found!");
        }
        return subCategories;
    }

    public static List<Course> readCourses(List<SubCategory> subCategories, String file) throws IOException {
        ValidatorUtils.cantBeNull(subCategories);
        ValidatorUtils.cantBeNullOrEmpty(file);

        List<Course> courses = new ArrayList<>();

        try (Scanner scannerCourse = csvReader(file)) {
            scannerCourse.nextLine();
            while (scannerCourse.hasNextLine()) {
                String line = scannerCourse.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                if (!scannerCourse.hasNext()) {
                    System.out.println("Can't read this line: " + line + "from csv. The object subCategory should be not null!");
                    break;
                }

                Course newCourse = buildCourse(subCategories, lineScanner);
                courses.add(newCourse);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Course file or directory not found!");
        }
        return courses;
    }

    private static Scanner csvReader(String filePath) throws IOException {
        ValidatorUtils.cantBeNullOrEmpty(filePath);
        return new Scanner(new File(filePath));
    }

    private static Category buildCategory(Scanner lineScanner) {
        ValidatorUtils.cantBeNull(lineScanner);

        String name = lineScanner.next().trim();
        String code = lineScanner.next().trim();
        Integer order = Category.processingOrder(lineScanner.next());
        String description = lineScanner.next().trim();
        boolean active = Category.convertToBoolean(lineScanner.next());
        String imageUrl = lineScanner.next().trim();
        String colorCode = lineScanner.next().trim();

        Category newCategory = new Category(name, code);

        if (!order.equals(0)) {
            newCategory.setOrder(order);
        }

        newCategory.setShortDescription(description);
        newCategory.setActive(active);
        newCategory.setImageUrl(imageUrl);
        newCategory.setColorCode(colorCode);

        return newCategory;
    }

    private static SubCategory buildSubCategory(List<Category> categories, Scanner lineScanner) {
        ValidatorUtils.cantBeNull(lineScanner);

        String name = lineScanner.next();
        String code = lineScanner.next();
        Integer order = SubCategory.processingOrder(lineScanner.next());
        String description = lineScanner.next();

        boolean active = SubCategory.convertToBoolean(lineScanner.next());
        Optional<Category> possibleCategory = findCategoryByCode(categories, lineScanner.next());

        SubCategory newSubCategory = null;
        if (possibleCategory.isPresent()) {
            newSubCategory = new SubCategory(name, code, possibleCategory.get());

            if (!order.equals(0)) {
                newSubCategory.setOrder(order);
            }

            newSubCategory.setShortDescription(description);
            newSubCategory.setActive(active);
        }
        return newSubCategory;
    }

    private static Course buildCourse(List<SubCategory> subCategories, Scanner lineScanner) {
        ValidatorUtils.cantBeNull(lineScanner);

        String name = lineScanner.next().trim();
        String code = lineScanner.next().trim();
        Integer estimatedTimeInHours = lineScanner.nextInt();
        boolean visibility = Course.convertToBoolean(lineScanner.next());
        String targetAudience = lineScanner.next().trim();
        Instructor instructor = new Instructor(lineScanner.next().trim());
        String resume = lineScanner.next().trim();
        String developedSkills = Course.verifyDevelopedSkillsEmpty(lineScanner.next().trim());

        Optional<SubCategory> possibleSubCategory = findSubCategoryByCode(subCategories, lineScanner.next());
        Course newCourse = null;
        if (possibleSubCategory.isPresent()) {
            newCourse = new Course(name, code, estimatedTimeInHours, instructor, possibleSubCategory.get());
            newCourse.setVisibility(visibility);
            newCourse.setTargetAudience(targetAudience);
            newCourse.setResume(resume);
            newCourse.setDevelopedSkills(developedSkills);
        }
        return newCourse;
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
