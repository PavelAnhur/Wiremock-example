package com.epam.api.wiremock.program;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
public class GetTestWithProgramMockServer extends GetQueryWithProgramMockConditions {

    @Test
    public void wireMockGETTestWithSecondEndPoint() {
        SoftAssert softAssert = new SoftAssert();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(
                format(getUrl(), getPort()) + getEndPoint()))
                                      .GET()
                                      .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            log.info("Status code: {}", statusCode);
            softAssert.assertEquals(statusCode, HttpStatus.SC_OK, "Status code is not correct!");
            Optional<String> header = response.headers().firstValue("Content-Type");
            log.info("Response headers: {}", header.toString());
            softAssert.assertEquals(header, Optional.of("applications/json"),
                    "Content type is not correct!");
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
        softAssert.assertAll();
    }
}
