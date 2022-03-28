<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/atualizaCategorias" var="linkServletAlteraCategoria"/>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>Edição de Categoria: </h1>
    <form action="${linkServletAlteraCategoria}" method="post">

        <fieldset>
            <legend>Dados de categorias</legend>
            <table>
                <tr>
                    <td>
                        <label>Nome: </label>
                    </td>
                    <td>
                        <input type="text" name="name" value="${category.name}"/>
                    </td>
                    <td>
                        <label>Código: </label>
                    </td>
                    <td>
                        <input type="text" name="code" value="${category.code}"/>
                    </td>
                    <td>
                        <label>Descrição: </label>
                    </td>
                    <td>
                        <textarea name="shortDescription">${category.shortDescription}</textarea>
                    </td>
                    <td>
                        <label>Visibilidade: </label>
                    </td>
                    <td>
                        <input id="true" type="radio" name="active" value="true" value="${category.isActive()}" checked/>
                        <label for="true">true</label><br>
                        <input id="false" type="radio" name="active" value="false" value="${category.isActive()}"/>
                        <label for="false">false</label>
                    </td>
                    <td>
                        <label>Guia de estudo: </label>
                    </td>
                    <td>
                        <textarea name="studyGuide">${category.studyGuide}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Ordem: </label>
                    </td>
                    <td>
                        <input type="number" name="order" value="${category.order}"/>
                    </td>
                    <td>
                        <label>Imagem: </label>
                    </td>
                    <td>
                        <input type="text" name="imageUrl" value="${category.imageUrl}"/>
                    </td>
                    <td>
                        <label>Código da cor: </label>
                    </td>
                    <td>
                        <input type="text" name="colorCode" value="${category.colorCode}" />
                    </td>
                    <td><input type="hidden" name="id" value="${category.id }"></td>
                </tr>
            </table>
        </fieldset>

        <input type="submit" />

    </form>

</body>
</html>
