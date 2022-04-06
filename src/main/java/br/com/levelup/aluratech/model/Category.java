package br.com.levelup.aluratech.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

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
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "color_code")
    private String colorCode;

    @Deprecated
    public Category() {
    }

    public Category(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String shortDescription, String studyGuide, Integer order,
                    String imageUrl, String colorCode) {
        this.name = name;
        this.code = code;
        this.shortDescription = shortDescription;
        this.studyGuide = studyGuide;
        this.order = order;
        this.imageUrl = imageUrl;
        this.colorCode = colorCode;
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

    public boolean isActive() {
        return active;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Integer getOrder() {
        return order;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
