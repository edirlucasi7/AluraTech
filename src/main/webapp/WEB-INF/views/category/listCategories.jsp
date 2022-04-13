<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <title>Listagem de Categorias</title>
</head>
<body>

    <section class="container">
        <div>
            <h1>Categorias</h1>
            <a href="/admin/categories/new">
                <button class="btn btn-primary">Nova categoria</button>
            </a>
        </div>
        <div class="tableCategories">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Código</th>
                        <th>Status</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categories}" var="category">
                        <tr class="categories">
                            <td class="col-md-4">${category.name}</td>
                            <td class="col-md-4">${category.code}</td>
                            <td class="col-md-2 infoActive${category.id}">${category.isActive() ? "Ativa" : "Inativa"}</td>
                            <td class="col-md-1"><a href="/admin/subcategories/${category.code}">Subcategorias</a></td>
                            <td class="col-md-1">
                                <c:if test="${category.isActive()}">
                                    <a>
                                        <button class="btn btn-danger buttonDisableCategory${category.id}" onclick="disableCategory(${category.id})">Desativar</button>
                                    </a>
                                </c:if>
                            </td>
                            <td class="col-md-1">
                                <a href="/admin/categories/${category.code}">
                                    <button id="editButton" class="btn btn-light">Editar</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
    <script src="/resources/js/category/disableCategory.js"></script>
</body>
</html>