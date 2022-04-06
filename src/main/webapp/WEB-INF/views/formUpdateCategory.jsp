<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <title>Atualiza Categoria</title>
</head>
<body>

    <section class="container">
        <h1>Atualiza Categoria</h1>
        <form:form modelAttribute="updateCategoryRequest" method="post">
            <div class="form-group">
                <form:input class="form-control" type="hidden" placeholder="Digite aqui o nome da categoria" path="id"/>
            </div>

            <div class="form-group">
                <label for="name">Nome:</label>
                <form:input class="form-control" type="text" placeholder="Digite aqui o nome da categoria" path="name"/>
                <form:errors path="name" />
            </div>

            <div class="form-group">
                <label for="code">Código:</label>
                <form:input class="form-control" type="text" path="code" placeholder="por exemplo: desenvolvimento, mobile, (não use letras maiúsculas, acentos ou caracteres especiais)"/>
                <form:errors path="code" />
            </div>

            <div class="row">
                <div class="form-check col-md-2">
                    <input class="form-check-input" type="checkbox" name="active" value=true id="flexCheckDefault"
                            <c:if test="${category.isActive() == true}">checked="checked"</c:if>
                    />
                    <label class="form-check-label categoryCheck" for="flexCheckDefault">
                        Categoria Ativa?
                    </label>
                </div>
                <div class="activeCategoryInfo col-md-10">
                    <p>Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc</p>
                </div>
            </div>

            <div class="form-group">
                <label for="order">Ordem da Categoria:</label>
                <form:input class="form-control" type="number" path="order" placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2"/>
            </div>

            <div class="form-group">
                <label for="studyGuide">Guias de estudo:</label>
                <form:textarea class="form-control" path="studyGuide" rows="10" placeholder="Um texto apontando para formações para ajudar pessoas perdidas"/>
            </div>

            <div class="form-group">
                <label for="imageUrl">Caminho do ícone:</label>
                <form:input class="form-control" type="text" path="imageUrl" placeholder="por exemplo: /imagens/categorias/programacao.svg"/>
            </div>

            <div class="form-group">
                <label for="colorCode">Cor:</label>
                <form:input class="form-control" path="colorCode" type="text" placeholder="por exemplo: #fcc14a"/>
                <form:errors path="colorCode" />
            </div>

            <div class="form-group">
                <label for="shortDescription">Descrição:</label>
                <form:input class="form-control" path="shortDescription" type="text" placeholder="por exemplo: iOS, Android, PhoneGap e mais..."/>
            </div>
            <button type="submit" class="btn btn-primary">Enviar</button>
        </form:form>

    </section>

    <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
            crossorigin="anonymous"></script>
</body>
</html>
