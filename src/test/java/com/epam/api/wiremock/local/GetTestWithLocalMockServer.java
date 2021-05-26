package com.epam.api.wiremock.local;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class GetTestWithLocalMockServer extends GetQueryWithLocalMockConditions {

    @Test
    public void wireMockGETTestWithFirstEndPoint() {
        SoftAssert softAssert = new SoftAssert();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(getUrl() + getEndpoint())).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            log.info("Response status code (local wireMock): {}", statusCode);
            softAssert.assertEquals(statusCode, HttpStatus.SC_OK);
            String responseBody = response.body();
            log.info("Response body (local wireMock): {}", responseBody);
            softAssert.assertEquals(responseBody, getBodyMessage());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
        softAssert.assertAll();
    }
}
