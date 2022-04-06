package br.com.levelup.aluratech.model.response;

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
    private final List<SubCategoryListResponse> subCategories = new ArrayList<>();
    private final List<CourseListResponse> courses = new ArrayList<>();

    public ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse(Category category, List<SubCategory> subCategory, List<Course> courses) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();
        this.totalOfCoursesByCategory = courses.size();
        this.subCategories.addAll(subCategory.stream().map(SubCategoryListResponse::new).collect(Collectors.toList()));
        this.courses.addAll(courses.stream().map(CourseListResponse::new).collect(Collectors.toList()));
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

    public List<SubCategoryListResponse> getSubCategories() {
        return subCategories;
    }

    public List<CourseListResponse> getCourses() {
        return courses;
    }
}
