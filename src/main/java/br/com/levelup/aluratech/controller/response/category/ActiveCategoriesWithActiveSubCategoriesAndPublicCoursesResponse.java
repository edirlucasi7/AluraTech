package br.com.levelup.aluratech.controller.response.category;

import br.com.levelup.aluratech.controller.response.course.CourseResponse;
import br.com.levelup.aluratech.controller.response.subcategory.SubCategoryResponse;
import br.com.levelup.aluratech.model.Category;
import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.SubCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse {

    private final String name;
    private final String code;
    private final Integer order;
    private final String colorCode;
    private final String studyGuide;
    private final int totalOfCoursesByCategory;
    private final List<SubCategoryResponse> subCategories = new ArrayList<>();
    private final List<CourseResponse> courses = new ArrayList<>();

    public ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse(Category category, List<SubCategory> subCategory, List<Course> courses) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();
        this.totalOfCoursesByCategory = courses.size();
        this.subCategories.addAll(subCategory.stream().map(SubCategoryResponse::new).collect(Collectors.toList()));
        this.courses.addAll(courses.stream().map(CourseResponse::new).collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getOrder() {
        return order;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public int getTotalOfCoursesByCategory() {
        return totalOfCoursesByCategory;
    }

    public List<SubCategoryResponse> getSubCategories() {
        return subCategories;
    }

    public List<CourseResponse> getCourses() {
        return courses;
    }
}
