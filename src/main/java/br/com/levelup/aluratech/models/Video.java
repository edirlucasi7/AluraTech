package br.com.levelup.aluratech.models;

import javax.persistence.*;

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

    @Deprecated
    public Video() {
    }

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        this.url = url;
    }
}
