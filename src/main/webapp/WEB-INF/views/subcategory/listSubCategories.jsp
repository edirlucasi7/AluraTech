<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <title>Listagem de Subcategorias</title>
</head>
<body>

    <section class="container">
        <div>
            <h3>${categoryName}</h3>
            <h1>Subcategorias</h1>
            <a href="/admin/subcategories/new">
                <button class="btn btn-primary">Nova Subcategoria</button>
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
                    <c:forEach items="${subCategories}" var="subcategory">
                        <tr class="categories">
                            <td class="col-md-4">${subcategory.name}</td>
                            <td class="col-md-4">${subcategory.code}</td>
                            <td class="col-md-2 infoActive${subcategory.id}">${subcategory.isActive() ? "Ativa" : "Inativa"}</td>
                            <td class="col-md-1">
                                <a href="/admin/courses/${subcategory.categoryCode}/${subcategory.code}">Cursos</a>
                            </td>
                            <td class="col-md-1">
                                <c:if test="${subcategory.isActive()}">
                                    <a>
                                        <button class="btn btn-danger buttonDisableSubCategory${subcategory.id}" onclick="disableSubCategory(${subcategory.id})">Desativar</button>
                                    </a>
                                </c:if>
                            </td>
                            <td class="col-md-1">
                                <a href="/admin/subcategories/${subcategory.categoryCode}/${subcategory.code}">
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
    <script src="/resources/js/disableSubCategory.js"></script>
</body>
</html>