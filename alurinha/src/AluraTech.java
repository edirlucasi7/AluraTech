import com.br.levelup.model.*;

public class AluraTech {

    public static void main(String[] args) {
        Instructor instructor = new Instructor("Nico");
        Course course = new Course("Java-1", "codigo-1", 20, instructor);
        System.out.println(course);

        Section section = new Section("Secao1", "codigo-secao-1", course);

        Activity activity = new Activity("Heranca", "codigo-secao-2", section);
        activity.setTipo(new Question("quando utilizar o this"));
        System.out.println(activity);
    }

}
