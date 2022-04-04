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

@WebServlet("/criaCategoria")
public class NewCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        EntityManager manager = JPAUtil.getEntityManager("alurinha");

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String shortDescription = request.getParameter("shortDescription");
        String activeString = request.getParameter("active");
        String studyGuide = request.getParameter("studyGuide");
        String orderString = request.getParameter("order");
        String imageUrl = request.getParameter("imageUrl");
        String colorCode = request.getParameter("colorCode");

        boolean active = Boolean.parseBoolean(activeString);
        int order = Integer.parseInt(orderString);

        Category category = new Category(name, code);
        category.setShortDescription(shortDescription);
        category.setActive(active);
        category.setStudyGuide(studyGuide);
        category.setOrder(order);
        category.setImageUrl(imageUrl);
        category.setColorCode(colorCode);

        CategoryDAO categoryDAO = new CategoryDAO(manager);
        manager.getTransaction().begin();
        categoryDAO.create(category);
        manager.getTransaction().commit();
        manager.close();

        response.sendRedirect("listaCategorias");
    }
}
