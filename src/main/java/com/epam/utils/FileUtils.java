package com.epam.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public final class FileUtils {
    private FileUtils() {
    }

    public static String getDataFromFile(final String path, final String fileName) {
        String dataFromFile = "";
        try {
            dataFromFile = new String(Files.readAllBytes(Paths.get(path + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFromFile;
    }
}
