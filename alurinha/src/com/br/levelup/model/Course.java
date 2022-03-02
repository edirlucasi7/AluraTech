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
    private Instructor instructor;
    private String resume;
    private String developedSkills;
    private SubCategory subCategory;

    public Course(String name, String code, Integer estimatedTimeInHours, Instructor instructor, SubCategory subCategory) {
        cantBeNull(name, "The field name should not be null!");
        containOnlyLettersLowercaseAndNumbersAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
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

    public static boolean convertToBoolean(String stringActive) {
        return "PRIVADA".equals(stringActive);
    }

    public static boolean existsPrivate(List<Course> courses) {
        return courses.stream().anyMatch(Course::getVisibility);
    }

    public static Set<Instructor> instructorsNames(List<Course> courses) {
        return courses.stream().map(Course::getInstructor).collect(Collectors.toSet());
    }

    public static Map<String, Long> instructorNamesAndCourses(List<Course> courses) {
        return courses.stream().collect(Collectors.toMap(
                c -> c.getInstructorName(),
                c -> totalOfCoursesByInstructor(courses, c.getInstructorName()),
                (name1, name2) -> name1));
    }

    private static long totalOfCoursesByInstructor(List<Course> courses, String instructorNames) {
        return courses.stream().filter(c -> instructorNames.equals(c.getInstructorName())).count();
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
