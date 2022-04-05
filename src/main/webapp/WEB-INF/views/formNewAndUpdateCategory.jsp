<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <title>Cadastro da Categoria</title>
</head>
<body>

    <section class="container">
        <h1>Nova Categoria</h1>

        <form action="/admin/categories/new" method="post">
            <div class="form-group">
                <label for="categoryName">Nome:</label>
                <input class="form-control" id="categoryName" name="name" type="text" placeholder="Digite aqui o nome da categoria" required>
            </div>

            <div class="form-group">
                <label for="categoryCode">Código:</label>
                <input class="form-control" id="categoryCode" name="code" type="text" placeholder="por exemplo: desenvolvimento, mobile, (não use letras maiúsculas, acentos ou caracteres especiais)" required>
            </div>

            <div class="row">
                <div class="form-check col-md-2">
                    <input class="form-check-input" type="checkbox" name=active value=true id="flexCheckDefault" />
                    <label class="form-check-label categoryCheck" for="flexCheckDefault">
                        Categoria Ativa?
                    </label>
                </div>
                <div class="activeCategoryInfo col-md-10">
                    <p>Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações, etc</p>
                </div>
            </div>

            <div class="form-group">
                <label for="categoryOrder">Ordem da Categoria:</label>
                <input class="form-control" id="categoryOrder" type="number" name="order" placeholder="por exemplo: categoria de ordem 1 aparece antes da categoria de ordem 2">
            </div>

            <div class="form-group">
                <label for="categoryStudyGuide">Guias de estudo:</label>
                <textarea class="form-control" id="categoryStudyGuide" name="studyGuide" rows="10" placeholder="Um texto apontando para formações para ajudar pessoas perdidas"></textarea>
            </div>

            <div class="form-group">
                <label for="categoryUrlCode">Caminho do ícone:</label>
                <input class="form-control" id="categoryUrlCode" type="text" name="imageUrl" placeholder="por exemplo: /imagens/categorias/programacao.svg">
            </div>

            <div class="form-group">
                <label for="categoryColorCode">Cor:</label>
                <input class="form-control" id="categoryColorCode" type="text" name="colorCode" placeholder="por exemplo: #fcc14a">
            </div>

            <div class="form-group">
                <label for="categoryShortDescription">Descrição:</label>
                <input class="form-control" id="categoryShortDescription" type="text" name="shortDescription" placeholder="por exemplo: iOS, Android, PhoneGap e mais...">
            </div>
            <button type="submit" class="btn btn-primary">Enviar</button>
        </form>

    </section>

    <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
            crossorigin="anonymous"></script>
</body>
</html>
