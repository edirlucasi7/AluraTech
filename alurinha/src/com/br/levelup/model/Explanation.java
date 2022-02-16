package com.br.levelup.model;

import static validators.ObjectValidator.cantBeNull;
import static validators.StringValidator.cantBeNullOrEmpty;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);
        cantBeNullOrEmpty(text, "The field text should not be null or empty!");
        cantBeNull(section, "The object section should not be null!");
        this.text = text;
    }

    @Override
    public String toString() {
        return "Explanation{" +
                "text='" + text + '\'' +
                '}';
    }

}
