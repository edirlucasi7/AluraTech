package br.com.levelup.aluratech.model;

import javax.persistence.*;

@Entity
@Table(name = "subcategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;
    @Column(name = "study_guide", columnDefinition = "TEXT")
    private String studyGuide;
    private boolean active;
    @Column(name = "order_visualization")
    private Integer order;
    @ManyToOne
    private Category category;

    @Deprecated
    public SubCategory() {
    }

    public SubCategory(String name, String code, Category category) {
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", category=" + category +
                '}';
    }
}
