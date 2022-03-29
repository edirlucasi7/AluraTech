package com.br.levelup.servlet;

import com.br.levelup.dao.CategoryDAO;
import com.br.levelup.model.Category;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/atualizaVisibilidadeCategorias")
public class UpdateCategoryVisibiltyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        EntityManager manager = JPAUtil.getEntityManager("alurinha");

        String idString = request.getParameter("id");
        Long id = Long.parseLong(idString);

        CategoryDAO categoryDAO = new CategoryDAO(manager);
        Category category = categoryDAO.findById(id).get();

        manager.getTransaction().begin();
        category.disableActive(category.isActive());
        manager.getTransaction().commit();
        manager.close();
    }

}
