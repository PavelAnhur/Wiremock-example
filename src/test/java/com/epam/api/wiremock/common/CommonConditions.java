package com.epam.api.wiremock.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CommonConditions {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static String getUrl() {
        return String.format("http://%s:%s", HOST, PORT);
    }

    public static int getPort() {
        return PORT;
    }

    public static String getHost() {
        return HOST;
    }

    public void testResultsHead() {
        log.info("Test - {}", this.getClass().getSimpleName());
    }
}
