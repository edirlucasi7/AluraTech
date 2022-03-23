package com.br.levelup.model;

import javax.persistence.*;

import static com.br.levelup.model.utils.ValidatorUtils.*;

@Entity
@Table(name = "video")
public class Video extends Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Column(name = "duration_in_minutes", columnDefinition = "TINYINT")
    private Integer durationInMinutes;
    @Column(columnDefinition = "TEXT")
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        cantBeNullOrEmpty(url, "The field url should not be null or empty!");
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" +
                "url='" + url + '\'' +
                ", minute=" + durationInMinutes +
                ", transcription='" + transcription + '\'' +
                '}';
    }

}
