package br.com.levelup.aluratech.models;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    private static final Integer ESTIMATED_TIME_MIN = 1;
    private static final Integer ESTIMATED_TIME_MAX = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @Column(name = "estimated_time_in_hours", columnDefinition = "TINYINT")
    private Integer estimatedTimeInHours;
    private boolean visibility;
    @Column(name = "target_audience", columnDefinition = "TEXT")
    private String targetAudience;
    @Column(columnDefinition = "TEXT")
    private String resume;
    @Column(name = "developed_skills", columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;
    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private SubCategory subCategory;

    @Deprecated
    public Course() { }

    public Course(String name, String code, Integer estimatedTimeInHours, Instructor instructor, SubCategory subCategory) {
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.instructor = instructor;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", estimatedTimeInHours=" + estimatedTimeInHours +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", resume='" + resume + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                ", instructor=" + instructor +
                ", subCategory=" + subCategory +
                '}';
    }
}
