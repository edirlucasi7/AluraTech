package br.com.levelup.aluratech.models;
import javax.persistence.*;

@Entity
@Table(name = "activity")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private boolean active;
    @Column(name = "order_visualization")
    private Integer order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @Deprecated
    public Activity() {
    }

    public Activity(String title, String code, Section section) {
        this.title = title;
        this.code = code;
        this.section = section;
    }
}
