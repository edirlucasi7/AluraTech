<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/atualizaCategoria" var="linkServletAlteraCategoria"/>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro da categoria</title>
</head>
<body>

    <h1>Edição da Categoria: </h1>
    <form action="${linkServletAlteraCategoria}" method="post">

        <fieldset>
            <legend>Dados da categoria</legend>
            <table>
                <tr>
                    <td>
                        <label>Nome: </label>
                    </td>
                    <td>
                        <input type="text" name="name" value="${category.name}" required="true"/>
                    </td>
                    <td>
                        <label>Código: </label>
                    </td>
                    <td>
                        <input type="text" name="code" value="${category.code}" required="true"/>
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
                        <input id="true" type="radio" name="active" value="true" checked/>
                        <label for="true">ATIVA</label><br>
                        <input id="false" type="radio" name="active" value="false" />
                        <label for="false">INATIVA</label>
                    </td>
                    <td>
                        <label>Guia: </label>
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
                        <input type="number" name="order" value="${category.order}" required="true"/>
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
                        <input type="text" name="colorCode" value="${category.colorCode}" required="true"/>
                    </td>
                    <td><input type="hidden" name="id" value="${category.id }"></td>
                </tr>
            </table>
        </fieldset>

        <input type="submit" />

    </form>

</body>
</html>
