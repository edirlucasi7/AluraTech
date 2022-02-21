package com.br.levelup.model;

import static com.br.levelup.model.utils.ValidatorUtils.*;

public class Video extends Activity{

    private String url;
    private Integer durationInMinutes;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        cantBeNullOrEmpty(url, "The field url should not be null or empty!");
        this.url = url;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        cantBeLessZero(durationInMinutes, "The field durationInMinutes should not be less or equal zero or null!");
        this.durationInMinutes = durationInMinutes;
    }

    public void setTranscription(String transcription) {
        cantBeNullOrEmpty(transcription, "The field transcription should not be empty!");
        this.transcription = transcription;
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
