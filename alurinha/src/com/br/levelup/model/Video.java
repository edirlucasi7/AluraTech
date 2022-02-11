package com.br.levelup.model;

import com.br.levelup.model.enums.Indication;

public class Video extends Activity{

    private String url;
    private int minute;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        this.url = url;
    }

    public Video(String title, String code, Indication indication, Integer order, Section section, String url, int minute, String transcription) {
        super(title, code, indication, order, section);
        this.url = url;
        this.minute = minute;
        this.transcription = transcription;
    }

    @Override
    public String toString() {
        return "Video{" +
                "url='" + url + '\'' +
                ", minute=" + minute +
                ", transcription='" + transcription + '\'' +
                '}';
    }

}
