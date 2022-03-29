package com.br.levelup.servlet;

import com.br.levelup.dao.CategoryDAO;
import com.br.levelup.model.Category;
import com.br.levelup.model.dto.CategoryDTO;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listaCategorias")
public class CategoryListingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        EntityManager manager = JPAUtil.getEntityManager("alurinha");

        CategoryDAO categoryDAO = new CategoryDAO(manager);
        List<CategoryDTO> allCategories = CategoryDTO.convert(categoryDAO.getAllCategories());
        request.setAttribute("categorias", allCategories);

        RequestDispatcher rd =  request.getRequestDispatcher("/categoryListing.jsp");
        rd.forward(request, response);
    }
}
