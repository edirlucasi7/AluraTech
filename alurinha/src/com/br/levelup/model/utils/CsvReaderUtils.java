package com.br.levelup.model.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.br.levelup.model.utils.ValidatorUtils.cantBeNullOrEmpty;

public class CsvReaderUtils {

    public static Scanner csvReader(String filePath) throws IOException {
        cantBeNullOrEmpty(filePath);
        return new Scanner(new File(filePath));
    }

}
