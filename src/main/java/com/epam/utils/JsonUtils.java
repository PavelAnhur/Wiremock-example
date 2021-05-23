package com.epam.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public final class JsonUtils {
    private JsonUtils() {
    }

    public static String fromJsonFileToString(final String path, final String jsonFile) throws IOException {
        return FileUtils.readFileToString(
                new File(String.format("%s%s", path, jsonFile)), StandardCharsets.UTF_8);
    }
}
