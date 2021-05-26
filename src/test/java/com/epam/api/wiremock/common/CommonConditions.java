package com.epam.api.wiremock.common;

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
}
