package com.br.levelup.servlet;

import com.br.levelup.dao.CategoryDAO;
import com.br.levelup.model.Category;
import com.br.levelup.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    private EntityManager manager = JPAUtil.getEntityManager("alurinha");;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CategoryDAO categoryDAO = new CategoryDAO(manager);
        List<Category> allCategories = categoryDAO.getAllCategories();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Informações de Categorias: </h2>");
        out.println("<table><tr>");
        out.println("<th>Nome</th>");
        out.println("<th>Código</th>");
        out.println("<th>Descrição</th>");
        out.println("<th>Visibilidade</th>");
        out.println("<th>Ordem</th>");
        out.println("<th>Imagem</th>");
        out.println("<th>Color code</th>");
        out.println("</tr>");
        for (Category category : allCategories) {
            out.println("<tr><td>" + category.getName() + "</td>");
            out.println("<td>" + category.getCode() + "</td>");
            out.println("<td>" + category.getShortDescription() + "</td>");
            out.println("<td>" + category.isActive() + "</td>");
            out.println("<td>" + category.getOrder() + "</td>");
            out.println("<td>" + category.getImageUrl() + "</td>");
            out.println("<td>" + category.getColorCode() + "</td></tr>");
        }
        out.println("</table>");
        out.println("</body></html>");

    }
}
