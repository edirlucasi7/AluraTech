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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listaCategorias")
public class CategoryListingServlet extends HttpServlet {

    private EntityManager manager = JPAUtil.getEntityManager("alurinha");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CategoryDAO categoryDAO = new CategoryDAO(manager);
        List<Category> allCategories = categoryDAO.getAllCategories();
        request.setAttribute("categorias", allCategories);

        RequestDispatcher rd =  request.getRequestDispatcher("/listaCategorias.jsp");
        rd.forward(request, response);

    }
}
