package br.com.levelup.aluratech.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static br.com.levelup.aluratech.model.utils.ValidatorUtils.cantBeNullOrEmpty;

@Entity
@Table(name = "video")
public class Video extends Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "A url não pode ser vazia!")
    private String url;
    @Column(name = "duration_in_minutes", columnDefinition = "TINYINT")
    private Integer durationInMinutes;
    @Column(columnDefinition = "TEXT")
    private String transcription;

    @Deprecated
    public Video() {
    }

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        cantBeNullOrEmpty(url, "The field url should not be null or empty!");
        this.url = url;
    }
}
