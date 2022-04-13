<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
        <title>Dashboard Administrativo</title>
    </head>
    <body>

        <section class="container center">
            <div>
                <h1>Cursos por categoria</h1>
            </div>
            <div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Quantidade de cursos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allCoursesByCategory}" var="courseByCategory">
                            <tr>
                                <td class="col-md-6">${courseByCategory.name}</td>
                                <td class="col-md-6">${courseByCategory.amount}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

        <section class="container center">
            <div>
                <h1>Instrutor com mais cursos</h1>
            </div>
            <div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Quantidade de cursos</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${instructorWithMoreCourses}" var="instructorWithCourses">
                            <tr>
                                <td class="col-md-6">${instructorWithCourses.name}</td>
                                <td class="col-md-6">${instructorWithCourses.amount}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

        <script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
                crossorigin="anonymous"></script>
    </body>
</html>