package com.br.levelup.model;

import java.util.*;
import java.util.stream.Collectors;

import static com.br.levelup.model.utils.EstimateValuesUtils.minimumAndMaximumValue;
import static com.br.levelup.model.utils.ValidatorUtils.*;

public class Course {

    private static final Integer ESTIMATED_TIME_MIN = 1;
    private static final Integer ESTIMATED_TIME_MAX = 20;

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private boolean visibility;
    private String targetAudience;
    private String resume;
    private String developedSkills;
    private Instructor instructor;
    private SubCategory subCategory;

    public Course(String name, String code, Integer estimatedTimeInHours, Instructor instructor, SubCategory subCategory) {
        cantBeNullOrEmpty(name, "The field name should not be empty!");
        containOnlyLettersLowercaseAndNumbersAndDash(code);
        isBetween(estimatedTimeInHours, "The field stimated time should not be out of time range!");
        cantBeNull(instructor);
        cantBeNull(subCategory, "The object subCategory should not be null!");
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getResume() {
        return resume;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    private boolean getVisibility() {
        return this.visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setTargetAudience(String targetAudience) {
        cantBeNullOrEmpty(targetAudience, "The field targetAudience should not be null or empty!");
        this.targetAudience = targetAudience;
    }

    public void setResume(String resume) {
        cantBeNullOrEmpty(resume, "The field courseResume should not be null or empty!");
        this.resume = resume;
    }

    public void setDevelopedSkills(String developedSkills) {
        cantBeNullOrEmpty(developedSkills, "The field developedSkills should not be empty!");
        this.developedSkills = developedSkills;
    }

    public String getCategoryCode() {
        return subCategory.getCategoryCode();
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public String getSubCategoryCode() {
        return this.subCategory.getCode();
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public String getInstructorName() {
        return this.instructor.getName();
    }

    private void isBetween(Integer field, String error) {
        if(!minimumAndMaximumValue(field, ESTIMATED_TIME_MIN, ESTIMATED_TIME_MAX)) {
            throw new IllegalArgumentException(error);
        }
    }

    public static String verifyDevelopedSkillsEmpty(String skills) {
        return "".equals(skills) ? "Uninformed skills" : skills;
    }

    public static boolean convertToBoolean(String stringVisibility) {
        return "PÚBLICA".equals(stringVisibility);
    }

    public static boolean existsPrivate(List<Course> courses) {
        return courses.stream().anyMatch(c -> c.getVisibility() == false);
    }

    public static Set<Instructor> instructorsNames(List<Course> courses) {
        return courses.stream().map(Course::getInstructor).collect(Collectors.toSet());
    }

    public static Map<String, Long> instructorNamesAndCourses(List<Course> courses) {
        return courses.stream().collect(Collectors.toMap(
                Course::getInstructorName,
                c -> totalOfCoursesByInstructor(courses, c.getInstructorName()),
                (name1, name2) -> name1));
    }

    private static long totalOfCoursesByInstructor(List<Course> courses, String instructorName) {
        return courses.stream().filter(c -> instructorName.equals(c.getInstructorName())).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) && Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", estimatedTime=" + estimatedTimeInHours +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructor=" + instructor +
                ", resume='" + resume + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }

}
