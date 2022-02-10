package com.br.levelup.model;

import validators.StringValidator;

public class Video implements ActivityType {

    private String url;
    private int minute;
    private String transcription;

    public Video(String url) {
        StringValidator.cantBeNotEmpty(url, "The field url should not be empty!");
        this.url = url;
    }
}
