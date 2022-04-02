<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/criaCategoria" var="linkServletNovaCategoria"/>
<html>
<head>
    <title>Cadastro da Categoria</title>
</head>
<body>

    <h1>Cadastro da Categoria: </h1>
    <form action="${linkServletNovaCategoria}" method="post">

        <fieldset>
            <legend>Dados de categorias</legend>
            <table>
                <tr>
                    <td>
                        <label>Nome: </label>
                    </td>
                    <td>
                        <input type="text" name="name" required="true"/>
                    </td>
                    <td>
                        <label>Código: </label>
                    </td>
                    <td>
                        <input type="text" name="code" required="true"/>
                    </td>
                    <td>
                        <label>Descrição: </label>
                    </td>
                    <td>
                        <textarea name="shortDescription"></textarea>
                    </td>
                    <td>
                        <label>Visibilidade: </label>
                    </td>
                    <td>
                        <input id="true" type="radio" name="active" value="true"/>
                        <label for="true">ATIVA</label><br>
                        <input id="false" type="radio" name="active" value="false"/>
                        <label for="false">INATIVA</label>
                    </td>
                    <td>
                        <label>Guia: </label>
                    </td>
                    <td>
                        <textarea name="studyGuide"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Ordem: </label>
                    </td>
                    <td>
                        <input type="number" name="order" required="true"/>
                    </td>
                    <td>
                        <label>Imagem: </label>
                    </td>
                    <td>
                        <input type="text" name="imageUrl"/>
                    </td>
                    <td>
                        <label>Código da cor: </label>
                    </td>
                    <td>
                        <input type="text" name="colorCode" required="true"/>
                    </td>
                </tr>
            </table>
        </fieldset>

        <input type="submit" />

    </form>

</body>
</html>
