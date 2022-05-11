<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template-header title="Novo Curso">
        <section class="container">
            <h1>Novo Curso</h1>
            <form:form modelAttribute="newCourseRequest" method="post">
                <div class="form-group">
                    <label for="name">Nome:</label>
                    <form:input class="form-control" type="text" placeholder="Digite aqui o nome do curso aqui" path="name"/>
                    <form:errors path="name" />
                </div>

                <div class="form-group">
                    <label for="code">Código:</label>
                    <form:input class="form-control" type="text" path="code" placeholder="por exemplo: aprendendo-java, (não use letras maiúsculas, acentos ou caracteres especiais)"/>
                    <form:errors path="code" />
                </div>

                <div class="form-group">
                    <label for="estimatedTimeInHours">Tempo para finalização:</label>
                    <form:input class="form-control" type="number" path="estimatedTimeInHours" placeholder="o tempo deve estra entre 1 e 20 horas"/>
                    <form:errors path="estimatedTimeInHours" />
                </div>

                <div class="row">
                    <div class="form-check col-md-2">
                        <form:checkbox class="form-check-input" path="visibility" id="flexCheckDefault"/>
                        <label class="form-check-label categoryCheck" for="flexCheckDefault">
                            Curso Ativo?
                        </label>
                    </div>
                    <div class="activeCategoryInfo col-md-10">
                        <p>Mostra ou deixa de mostrar o curso na listagem dos alunos, de formações, etc</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="targetAudience">Público alvo:</label>
                    <form:input class="form-control" path="targetAudience" type="text" placeholder="Um texto apontando o público alvo do curso"/>
                </div>

                <div class="form-group">
                    <label for="idInstructor">Instrutor:</label>
                    <form:select class="contato-select form-control" path="idInstructor">
                        <form:options items="${instructors}" itemLabel="name" itemValue="id"></form:options>
                    </form:select>
                    <form:errors path="idInstructor"/>
                </div>

                <div class="form-group">
                    <label for="resume">Ementa:</label>
                    <form:textarea class="form-control" path="resume" rows="8" placeholder="Uma descrição detalhada do que será abordado no curso"/>
                </div>

                <div class="form-group">
                    <label for="developedSkills">Habilidade desenvolvidas:</label>
                    <form:input class="form-control" path="developedSkills" type="text" placeholder="por exemplo: Laravel, CakePHP e CodeIgniter são frameworks que você vai treinar bastante aqui."/>
                </div>

                <div class="form-group">
                    <label for="idSubCategory">Subcategoria:</label>
                    <form:select class="contato-select form-control" path="idSubCategory">
                        <form:options items="${subcategories}" itemLabel="name" itemValue="id"></form:options>
                    </form:select>
                    <form:errors path="idSubCategory"/>
                </div>

                <button type="submit" class="btn btn-primary">Enviar</button>
            </form:form>
        </section>
</templates:admin-template-header>
<templates:admin-template-footer>
</templates:admin-template-footer>
