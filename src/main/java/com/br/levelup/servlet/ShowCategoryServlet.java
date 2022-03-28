package com.br.levelup.servlet;

import com.br.levelup.dao.CategoryDAO;
import com.br.levelup.model.Category;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mostraCategoria")
public class ShowCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager manager = JPAUtil.getEntityManager("alurinha");

        String idString = request.getParameter("id");
        Long id = Long.parseLong(idString);

        CategoryDAO categoryDAO = new CategoryDAO(manager);
        Category category = categoryDAO.findById(id).get();

        request.setAttribute("category", category);
        RequestDispatcher rd = request.getRequestDispatcher("/formUpdateCategory.jsp");
        rd.forward(request, response);
    }
}
