package com.br.levelup.servlet;

import com.br.levelup.dao.CategoryDAO;
import com.br.levelup.model.Category;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/atualizaCategorias")
public class UpdateCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        EntityManager manager = JPAUtil.getEntityManager("alurinha");

        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String shortDescription = request.getParameter("shortDescription");
        String activeString = request.getParameter("active");
        String studyGuide = request.getParameter("studyGuide");
        String orderString = request.getParameter("order");
        String imageUrl = request.getParameter("imageUrl");
        String colorCode = request.getParameter("colorCode");

        Long id = Long.parseLong(idString);
        boolean active = Boolean.parseBoolean(activeString);
        int order = Integer.parseInt(orderString);

        CategoryDAO categoryDAO = new CategoryDAO(manager);
        Optional<Category> possibleCategory = categoryDAO.findById(id);
        if (possibleCategory.isPresent()) {
            Category category = possibleCategory.get();
            manager.getTransaction().begin();
            category.update(name, code, shortDescription, studyGuide, active, order, imageUrl, colorCode);
            manager.getTransaction().commit();
            manager.close();
        }
        response.sendRedirect("listaCategorias");
    }

}
