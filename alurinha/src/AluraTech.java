import com.br.levelup.model.*;
import com.br.levelup.model.enums.QuestionType;

public class AluraTech {

    public static void main(String[] args) {
//        System.out.println("********Category - Exception(name vazio)********");
//        Category categoryError1 = new Category("", "java-1");

//        System.out.println("********Category - Exception(code null)********");
//        Category categoryError2 = new Category("teste", null);

//        System.out.println("********Category - Exception(code fora do formato)********");
//        Category categoryError3 = new Category("teste", "java*");

        System.out.println("********Category********");
        Category category = new Category("teste", "java-1");
        category.setShortDescription("descricao pequena");
        category.setStudyGuide("descricao grande que serve como guia de estudo!");
//        System.out.println("********Category - Exception(studyGuide vazio)********");
//        category.setStudyGuide("");
        category.setActive(true);
        category.setOrder(1);
        category.setImageUrl("https://alura");
//        System.out.println("********Category - Exception(colorCode fora do formato)********");
//        category.setColorCode("FF57");
        category.setColorCode("#FF5733");
        System.out.println(category);

//        System.out.println("********SubCategory - Exception(category null)********");
//        SubCategory subCategoryError = new SubCategory("name", "code", null);

        System.out.println("********SubCategory********");
        SubCategory subCategory = new SubCategory("name", "code", category);
        subCategory.setActive(true);
//        System.out.println("********SubCategory - Exception(shortDescription vazio)********");
//        subCategory.setShortDescription("");
        subCategory.setShortDescription("pequena descricao");
//        System.out.println("********SubCategory - Exception(studyGuide vazio)********");
//        subCategory.setStudyGuide("");
        subCategory.setStudyGuide("testetesteteste");
        System.out.println(subCategory);


        Instructor instructor = new Instructor("Nico");
//        System.out.println("********CourseWithSubCategory - Exception(instructor null)********");
//        Course courseWithSubCategoryError1 = new Course("java basico", "java-1", 12, null, subCategory);

//        System.out.println("********CourseWithSubCategory - Exception(subCategory null)********");
//        Course courseWithSubCategoryError2 = new Course("java basico", "java-1", 12, instructor, null);

        System.out.println("********CourseWithSubCategory********");
        Course courseWithSubCategory = new Course("java basico", "java-1", 12, instructor, subCategory);
        courseWithSubCategory.setVisibility(false);
//        System.out.println("********Category - Exception(targetAudience vazio)********");
//        courseWithSubCategory.setTargetAudience("");
        courseWithSubCategory.setTargetAudience("Desenvolvedores Java");
        courseWithSubCategory.setResume("ementa do curso");
        courseWithSubCategory.setDevelopedSkills("habilidades de java e logica");
        System.out.println(courseWithSubCategory);

        Section section = new Section("Secao1", "codigo-secao-1", courseWithSubCategory);
        section.setActive(true);
        section.setOrder(2);
        section.setTest(true);

        System.out.println("********Question********");
        Question question = new Question("title", "java-1", section, "descricao");
        question.setType(QuestionType.MULTIPLE_ANSWERS);
//        System.out.println("********Question - Exception(type of QuestionType null)********");
//        question.setType(null);
        System.out.println(question);

        System.out.println("********Alternative********");
        Alternative alternative = new Alternative("Letra A", true, question);
        alternative.setJustification("correto!");
//        System.out.println("********Alternative - Exception(order null)********");
//        alternative.setOrder(null);
        alternative.setOrder(2);
        System.out.println(alternative);

//        System.out.println("********Explanation - Exception(section null)********");
//        Explanation explanationError = new Explanation("title", "java-1", null,"descricao");

        System.out.println("********Explanation********");
        Explanation explanation = new Explanation("title", "java-1", section,"descricao");
        System.out.println(explanation);

        System.out.println("********Video********");
        Video video = new Video("title", "java-1", section,"https://alura");
        video.setActive(true);
//        System.out.println("********Video - Exception(order null)********");
//        video.setOrder(null);
        video.setOrder(1);
        video.setDurationInMinutes(8);
        System.out.println(video);

    }

}
