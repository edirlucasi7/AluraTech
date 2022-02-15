import com.br.levelup.model.*;
import com.br.levelup.model.enums.QuestionType;

public class AluraTech {

    public static void main(String[] args) {
        System.out.println("********Category********");
        Category category = new Category("Programacao", "java-1", "short", "big", true,
                1, "https://alura", "#FF5733");
        System.out.println(category);

        System.out.println("********SubCategory********");
        SubCategory subCategory = new SubCategory("name", "code", category);
        System.out.println(subCategory);

        System.out.println("********CourseWithSubCategory********");

        Instructor instructor = new Instructor("Nico");
        Course courseWithSubCategory = new Course("Java-1", "codigo-1", 20, instructor, subCategory);
        System.out.println(courseWithSubCategory);

        Section section = new Section("Secao1", "codigo-secao-1", courseWithSubCategory);

        System.out.println("********Question********");
        Question question = new Question("title", "java-1", section, "descricao", QuestionType.MultipleAnswers);
        System.out.println(question);

        System.out.println("********Alternative********");
        Alternative alternative = new Alternative("Letra A", true, question);
        System.out.println(alternative);

        System.out.println("********Explanation********");
        Explanation explanation = new Explanation("title", "java-1", section,"descricao");
        System.out.println(explanation);

        System.out.println("********Video********");
        Video video = new Video("title", "java-1", section,"https://alura");
        System.out.println(video);

    }

}
