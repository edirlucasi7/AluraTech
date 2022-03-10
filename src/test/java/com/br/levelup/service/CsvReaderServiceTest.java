package com.br.levelup.service;

import com.br.levelup.model.Category;
import com.br.levelup.model.Course;
import com.br.levelup.model.Instructor;
import com.br.levelup.model.SubCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.br.levelup.service.CsvReaderService.*;

public class CsvReaderServiceTest {

    private static List<Category> categories;
    private static List<SubCategory> subCategories;
    private static List<Course> courses;

    @BeforeAll
    static void setUp() throws IOException {
        categories = readCategories("planilha-dados-escola - Categoria.csv");
        Assertions.assertEquals(3, categories.size());
        subCategories = readSubCategories(categories,"planilha-dados-escola - Subcategoria.csv");
        Assertions.assertEquals(5, subCategories.size());
        courses = readCourses(subCategories, "planilha-dados-escola - Curso.csv");
        Assertions.assertEquals(4, courses.size());
    }

    @Test
    void should_validate_existing_category() {
        Category category = new Category("Programação", "programacao", 1, "Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.",
                true, "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png",
                "#00c86f");

        Assertions.assertEquals(true, categories.contains(category));
    }

    @Test
    void should_validate_existing_subcategory() {
        SubCategory subCategory = new SubCategory("PHP", "php", new Category("Programação", "programacao"));
        subCategory.setOrder(3);
        subCategory.setShortDescription("PHP é uma das linguagens mais utilizadas.");

        Assertions.assertEquals(true, subCategories.contains(subCategory));
    }

    @Test
    void should_validate_existing_course() {
        Course course = new Course("Git e Github para Sobrevivência", "git-e-github-para-sobrevivencia",
                6, new Instructor("Mario Souto"),
                new SubCategory("builds-e-controle-de-versao", "builds-e-controle-de-versao", new Category("devops",
                        "devops")
                ));
        course.setVisibility(false);
        course.setTargetAudience("Desenvolvedores em qualquer linguagem ou plataforma que desejam mais segurança para seus projetos com as ferramentas de controle de versão Git e GitHub.");
        course.setResume("-O que é Git? <br> *Introdução <br> *Para que serve Git? <br> *Utilidade de um VCS <br> *Instalando o " +
                "Git <br> *Para saber mais: Instalação <br> *Repositórios <br> *Primeiros passos <br>  <br> -Iniciando os " +
                "trabalhos <br> *Salvando alterações <br> *Primeira configuração do Git <br> *Para saber mais: git status " +
                "<br> *Vendo o histórico <br> *Para saber mais: git log <br> *Ignorando arquivos <br> *Para saber mais: Quando commitar " +
                "<br>  <br> -Compartilhando o trabalho <br> *Repositórios remotos <br> *Servidor Git <br> *Trabalhando com " +
                "repositórios remotos <br> *Sincronizando os dados <br> *Compartilhamos as alterações <br> *GitHub <br> *Para " +
                "saber mais: GitHub <br>  <br> -Trabalhando em equipe <br> *Branches <br> *Para saber mais: Ramificações <br> *Unindo o " +
                "trabalho <br> *Merge de branches <br> *Atualizando a branch <br> *Rebase vs Merge <br> *Resolvendo conflitos <br> *Para saber " +
                "mais: Conflitos com rebase <br>  <br> -Manipulando as versões <br> *Ctrl+Z no Git <br> *Desfazendo o trabalho <br> *Guardando " +
                "para depois <br> *Salvando temporariamente <br> *Viajando no tempo <br> *Checkout <br>  <br> -Gerando entregas <br> *Vendo as " +
                "alterações <br> *Exibição das mudanças com o diff <br> *Tags e releases <br> *Tags no GitHub <br> *Consolidando o seu conhecimento");
        course.setDevelopedSkills("Descubra o que é Git e Github? <br> Entenda um sistema de controle de versão <br> Salve e recupere seu código em diferentes versões <br> " +
                "Resolva merges e conflitos <br> Trabalhe com diferentes branches");

        Assertions.assertEquals(true, courses.contains(course));
    }

}
