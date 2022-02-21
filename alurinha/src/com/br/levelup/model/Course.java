package com.br.levelup.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.br.levelup.model.utils.CsvReaderUtils.csvReader;
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

    private void isBetween(Integer field, String error) {
        if(!minimumAndMaximumValue(field, ESTIMATED_TIME_MIN, ESTIMATED_TIME_MAX)) {
            throw new IllegalArgumentException(error);
        }
    }

    public static boolean convertToBoolean(String stringActive) {
        return stringActive.equals("PRIVADA");
    }

    public static String verifyDevelopedSkillsEmpty(String skills) {
        return skills.equals("") ? "Uninformed skills" : skills;
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

            String name = lineScanner.next();
            String code = lineScanner.next().trim();
            Integer estimatedTimeInHours = lineScanner.nextInt();
            boolean visibility = Course.convertToBoolean(lineScanner.next());
            String targetAudience = lineScanner.next().trim();
            Instructor instructor = new Instructor(lineScanner.next());
            String resume = lineScanner.next().trim();
            String developedSkills = verifyDevelopedSkillsEmpty(lineScanner.next());

            if(!scannerCourse.hasNext()) {
                System.out.println("Can't read this line: " + line + "from csv. The object subCategory should be not null!\n");
                break;
            }

            SubCategory subCategory = SubCategory.findSubCategoryByCode(subCategories, lineScanner.next());

            Course newCourse = new Course(name, code, estimatedTimeInHours, instructor, subCategory);
            newCourse.setVisibility(visibility);
            newCourse.setTargetAudience(targetAudience);
            newCourse.setResume(resume);
            newCourse.setDevelopedSkills(developedSkills);
            courses.add(newCourse);

            lineScanner.close();
        }
        return courses;
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
