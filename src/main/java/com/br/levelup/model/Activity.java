package com.br.levelup.model;

import javax.persistence.*;

import static com.br.levelup.model.utils.ValidatorUtils.*;

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

    public Activity(String title, String code, Section section) {
        cantBeNullOrEmpty(title, "The field title should not be empty!");
        containOnlyLettersLowerCaseAndNumbersAndDash(code, "The field code must not be out of lowercase letters, numbers and dash format!");
        cantBeNull(section, "The object section should not be null!");
        this.title = title;
        this.code = code;
        this.section = section;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", indication=" + active +
                ", order=" + order +
                ", section=" + section +
                '}';
    }

}
