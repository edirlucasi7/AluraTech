<%@ page import="com.br.levelup.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Categorias</title>
</head>
<body>

    <h1>Informações de Categorias: </h1>
    <table>
        
        <tr>
            <th>Nome</th>
            <th>Código</th>
            <th>Descrição</th>
            <th>Visibilidade</th>
            <th>Ordem</th>
            <th>Imagem</th>
            <th>Código da cor</th>
        </tr>
        <%
            List<Category> allCategories = (List<Category>)request.getAttribute("categorias");
            for (Category category : allCategories) {
        %>
        <tr>
            <td><%=category.getName()%></td>
            <td><%=category.getCode()%></td>
            <td><%=category.getShortDescription()%></td>
            <td><%=category.isActive()%></td>
            <td><%=category.getOrder()%></td>
            <td><%=category.getImageUrl()%></td>
            <td><%=category.getColorCode()%></td>
        </tr>
        <%
            }
        %>
    </table>

</body>
</html>