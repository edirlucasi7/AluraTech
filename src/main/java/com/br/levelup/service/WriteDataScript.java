package com.br.levelup.service;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;

import java.io.PrintStream;
import java.util.List;

public class WriteDataScript {

    public static void loadInstructorData(PrintStream ps, List<Instructor> instructors) {

        instructors.forEach(instructor -> {

            String sqlTemplate = """
                    INSERT INTO instructor (name) VALUES("%s");       
                    """.formatted(instructor.getName());

            ps.println(sqlTemplate);
        });

    }

    public static void loadCategoryData(PrintStream ps, List<Category> categories) {

        categories.forEach(category -> {

            String sqlTemplate = """
                    INSERT INTO category (name, code, order_visualization, short_description, active, image_url, color_code) 
                    VALUES("%s", "%s", %d, "%s", %b, "%s", "%s");              
                    """.formatted(category.getName(), category.getCode(), category.getOrder(), category.getShortDescription(),
                    category.isActive(), category.getImageUrl(), category.getColorCode());

            ps.println(sqlTemplate);
        });

    }

    public static void loadSubCategoryData(PrintStream ps, List<SubCategory> subCategories) {

        subCategories.forEach(subCategory -> {

            String sqlFindCategoryByCode = """
                    (SELECT id FROM category where code = '%s')                      
                    """.formatted(subCategory.getCategoryCode());

            String sqlTemplate = """
                    INSERT INTO subcategory (name, code, order_visualization, short_description, active, category_id) 
                    VALUES("%s", "%s", %d, "%s", %b, %s);              
                    """.formatted(subCategory.getName(), subCategory.getCode(), subCategory.getOrder(), subCategory.getShortDescription(),
                    subCategory.isActive(), sqlFindCategoryByCode);

            ps.println(sqlTemplate);
        });
    }

    public static void loadCourseData(PrintStream ps, List<Course> courses) {

        courses.forEach(course -> {

            String sqlFindInstructorByName = """
                    (SELECT id FROM instructor where name = '%s')                       
                    """.formatted(course.getInstructorName());

            String sqlFindSubCategoryByCode = """
                    (SELECT id FROM subcategory where code = '%s')                       
                    """.formatted(course.getSubCategoryCode());

            String sqlTemplate = """
                    INSERT INTO course (name, code, estimated_time_in_hours, visibility, target_audience, instructor_id,
                    resume, developed_skills, subcategory_id) VALUES("%s", "%s", %d, %b, "%s", %s, "%s", "%s", %s);              
                    """.formatted(course.getName(), course.getCode(), course.getEstimatedTimeInHours(), course.isVisibility(),
                    course.getTargetAudience(), sqlFindInstructorByName, course.getResume(), course.getDevelopedSkills(),
                    sqlFindSubCategoryByCode);

            ps.println(sqlTemplate);
        });
    }

}
