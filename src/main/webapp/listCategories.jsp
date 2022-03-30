<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listagem de Categorias</title>
</head>
<body>

    <h1>Informações de Categorias: </h1>
    <table>

        <tr>
            <th>Nome</th>
            <th>Código</th>
            <th>Descrição</th>
            <th>Visibilidade</th>
            <th>Guia de estudo</th>
            <th>Ordem</th>
            <th>Ícone</th>
            <th>Código da cor</th>
        </tr>
        <c:forEach items="${categorias}" var="category">
        <tr class="categories">
            <td hidden class="categoryId">${category.id}</td>
            <td>${category.name}</td>
            <td>${category.code}</td>
            <td>${category.shortDescription}</td>
            <td class="info-active${category.id}">${category.isActive() ? "ATIVA" : "INATIVA"}</td>
            <td>${category.studyGuide}</td>
            <td>${category.order}</td>
            <td><img src="${category.imageUrl}" width="50px"/></td>
            <td>${category.colorCode }</td>
            <td><button onclick="disableCategory(${category.id})" id="disable${category.id}">desativar visibilidade</button></td>
            <td><a href="/mostraCategoria?id=${category.id }">editar</a></td>
        </tr>
        </c:forEach>
    </table>
    <td><a href="/formNewCategory.jsp">cadastrar</a></td>

    <script src="disableCategory.js"></script>

</body>
</html>