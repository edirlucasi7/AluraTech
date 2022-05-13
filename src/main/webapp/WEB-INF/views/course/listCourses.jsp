<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template-header title="Listagem de Cursos" />
        <section class="container">
            <div>
                <h3>${subcategoryName}</h3>
                <h1>Cursos</h1>
                <a href="/admin/courses/new">
                    <button class="btn btn-primary">Novo Curso</button>
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${coursesBySubCategory.getContent()}" var="course">
                            <tr class="categories">
                                <td class="col-md-5">${course.name}</td>
                                <td class="col-md-4">${course.code}</td>
                                <td class="col-md-2">${course.isVisibility() ? "Público" : "Privado"}</td>
                                <td class="col-md-1">
                                    <a href="/admin/courses/${categoryCode}/${subcategoryCode}/${course.code}">
                                        <button id="editButton" class="btn btn-light">Editar</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <nav class="text-center" aria-label="Page navigation">
                    <ul class="pagination">
                        <c:set var="contentSize" value="${coursesBySubCategory.getContent().size()}"/>
                        <c:if test="${contentSize != 0}">
                            <c:set var="isFirst" value="${coursesBySubCategory.isFirst()}"/>
                            <li class="${isFirst ? 'disabled' : ''}">
                                <a href="/admin/courses/${categoryCode}/${subcategoryCode}?page=${isFirst ? coursesBySubCategory.getNumber() : (coursesBySubCategory.getNumber()-1)}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${coursesBySubCategory.getTotalPages()}" var="pageNumber">
                            <li>
                                <a href="/admin/courses/${categoryCode}/${subcategoryCode}?page=${pageNumber-1}">${pageNumber}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${contentSize != 0}">
                            <c:set var="isLast" value="${coursesBySubCategory.isLast()}"/>
                            <li class="${isLast ? 'disabled' : ''}">
                                <a href="/admin/courses/${categoryCode}/${subcategoryCode}?page=${isLast ? coursesBySubCategory.getNumber() : (coursesBySubCategory.getNumber()+1)}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </section>
<templates:admin-template-footer>
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
</templates:admin-template-footer>