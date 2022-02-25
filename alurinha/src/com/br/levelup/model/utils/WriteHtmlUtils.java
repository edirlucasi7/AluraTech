package com.br.levelup.model.utils;

import java.io.BufferedWriter;
import java.io.IOException;

public class WriteHtmlUtils {

    public static void writeStartTagsInHtml(BufferedWriter bw) throws IOException {
        String startHtml = """
                <html>
                    <head>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                    <head>
                    <body>
                        <div class="container">
                """;
        bw.write(startHtml);
    }

    public static void writeEndTagsInHtml(BufferedWriter bw) throws IOException {
        String endHtml = """
                        </div>
                    <body>
                <html>
                """;
        bw.write(endHtml);
    }

}
