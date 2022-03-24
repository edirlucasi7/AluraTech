package com.br.levelup;

import com.br.levelup.dao.CategoryDAO;
import com.br.levelup.dao.CourseDAO;
import com.br.levelup.dao.SubCategoryDAO;
import com.br.levelup.model.dto.CategoryDTO;
import com.br.levelup.model.dto.PublicCourseDTO;
import com.br.levelup.model.dto.SubCategoryDTO;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.br.levelup.model.utils.WriteHtmlUtils.writeEndTagsInHtml;
import static com.br.levelup.model.utils.WriteHtmlUtils.writeStartTagsInHtml;
import static com.br.levelup.service.HtmlWriterService.*;

public class WriteReportInHtml {

    public static void main(String[] args) throws IOException {

        EntityManager em = JPAUtil.getEntityManager("alurinha");
        try(PrintWriter ps = new PrintWriter("relatorio.html", "UTF-8");
            BufferedWriter bw = new BufferedWriter(ps)) {

            CategoryDAO categoryDAO = new CategoryDAO(em);
            SubCategoryDAO subCategoryDAO = new SubCategoryDAO(em);
            CourseDAO courseDAO = new CourseDAO(em);

            List<CategoryDTO> categoryDTOS = CategoryDTO.convert(categoryDAO.getActiveCategoriesSorted());
            List<SubCategoryDTO> activeSubCategoryDTOs = SubCategoryDTO.convert(subCategoryDAO.getActiveSubCategoriesSortedByOrder());
            List<PublicCourseDTO> publicCourseDTOS = PublicCourseDTO.convert(courseDAO.getDataFromPublicCourses());
            List<String> subCategoryDTOsWithoutDescription = subCategoryDAO.getNamesFromSubCategoriesWithoutDescription();

            writeStartTagsInHtml(bw);
            writeHtmlActiveCategories(categoryDTOS, bw);
            writeHtmlActiveSubCategories(activeSubCategoryDTOs, bw);
            writeHtmlCourse(publicCourseDTOS, bw);
            writeHtmlSubCategories(subCategoryDTOsWithoutDescription, bw);
            writeEndTagsInHtml(bw);
        }
        em.close();
    }
}
