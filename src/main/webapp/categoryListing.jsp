<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <th>Guia de estudo</th>
            <th>Ordem</th>
            <th>Imagem</th>
            <th>Código da cor</th>
        </tr>
        <c:forEach items="${categorias}" var="category">
        <tr>
            <td>${category.name }</td>
            <td>${category.code }</td>
            <td>${category.shortDescription }</td>
            <td>${category.isActive() }</td>
            <td>${category.studyGuide }</td>
            <td>${category.order }</td>
            <td>${category.imageUrl }</td>
            <td>${category.colorCode }</td>
            <td><a href="/mostraCategoria?id=${category.id }">editar</a></td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>