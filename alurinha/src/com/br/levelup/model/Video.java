package com.br.levelup.model;

import validators.StringValidator;

public class Video extends Activity{

    private String url;
    private int minute;
    private String transcription;

    public Video(String title, String code, Section section, String url) {
        super(title, code, section);
        StringValidator.cantBeNotEmpty(url, "The field url should not be empty!");
        this.url = url;
    }

    public Video(String title, String code, Boolean active, Integer order, Section section, String url, int minute, String transcription) {
        super(title, code, active, order, section);
        StringValidator.cantBeNotEmpty(url, "The field url should not be empty!");
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
