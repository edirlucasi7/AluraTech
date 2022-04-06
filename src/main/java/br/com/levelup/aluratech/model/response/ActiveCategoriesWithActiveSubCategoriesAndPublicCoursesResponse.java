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
    private final List<SubCategoryListResponse> subCategoriesListResponse = new ArrayList<>();
    private final List<CourseListResponse> courseListResponse = new ArrayList<>();

    public ActiveCategoriesWithActiveSubCategoriesAndPublicCoursesResponse(Category category, List<SubCategory> subCategory, List<Course> courses) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();
        this.totalOfCoursesByCategory = courses.size();
        this.subCategoriesListResponse.addAll(subCategory.stream().map(s -> new SubCategoryListResponse(s)).collect(Collectors.toList()));
        this.courseListResponse.addAll(courses.stream().map(c -> new CourseListResponse(c)).collect(Collectors.toList()));
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

    public List<SubCategoryListResponse> getSubCategoriesListResponse() {
        return subCategoriesListResponse;
    }

    public List<CourseListResponse> getCourseListResponse() {
        return courseListResponse;
    }
}
