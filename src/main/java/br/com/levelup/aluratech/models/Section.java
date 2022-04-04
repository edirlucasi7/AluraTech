package br.com.levelup.aluratech.models;

import javax.persistence.*;

@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @Column(name = "order_visualization")
    private Integer order;
    private boolean active;
    private boolean test;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Deprecated
    public Section() {
    }

    public Section(String name, String code, Course course) {
        this.name = name;
        this.code = code;
        this.course = course;
    }
}
