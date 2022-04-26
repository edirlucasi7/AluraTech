<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Nova Subcategoria</title>
    </head>
    <body>

        <section class="container">
            <h1>Nova Subcategoria</h1>
            <form:form modelAttribute="newSubCategoryRequest" method="post">
                <div class="form-group">
                    <label for="name">Nome:</label>
                    <form:input class="form-control" type="text" placeholder="Digite aqui o nome da subcategoria" path="name"/>
                    <form:errors path="name" />
                </div>

                <div class="form-group">
                    <label for="code">Código:</label>
                    <form:input class="form-control" type="text" path="code" placeholder="por exemplo: java, python (não use letras maiúsculas, acentos ou caracteres especiais)"/>
                    <form:errors path="code" />
                </div>

                <div class="row">
                    <div class="form-check col-md-2">
                        <form:checkbox class="form-check-input" path="active" id="flexCheckDefault"/>
                        <label class="form-check-label categoryCheck" for="flexCheckDefault">
                            Subcategoria Ativa?
                        </label>
                    </div>
                    <div class="activeCategoryInfo col-md-10">
                        <p>Mostra ou deixa de mostrar a subcategoria na listagem dos alunos, de formações, etc</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="order">Ordem da Subcategoria:</label>
                    <form:input class="form-control" type="number" path="order" placeholder="por exemplo: subcategoria de ordem 1 aparece antes da categoria de ordem 2"/>
                </div>

                <div class="form-group">
                    <label for="studyGuide">Guias de estudo:</label>
                    <form:textarea class="form-control" path="studyGuide" rows="10" placeholder="Um texto apontando para formações para ajudar pessoas perdidas"/>
                </div>

                <div class="form-group">
                    <label for="shortDescription">Descrição:</label>
                    <form:input class="form-control" path="shortDescription" type="text" placeholder="por exemplo: Laravel, CakePHP e CodeIgniter são frameworks que você vai treinar bastante aqui."/>
                </div>

                <div class="form-group">
                    <label for="idCategory">Categoria:</label>
                    <form:select class="contato-select form-control" path="idCategory">
                        <form:options items="${categories}" itemLabel="name" itemValue="id"></form:options>
                    </form:select>
                    <form:errors path="idCategory"/>
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form:form>
        </section>

        <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
                crossorigin="anonymous"></script>
    </body>
</html>
