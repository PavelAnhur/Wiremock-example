package com.epam.api.wiremock.mocklab.POST;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@Slf4j
public class MockLabPOSTServerErrorTest extends MockLabPOSTTestsConditions {

    @Test
    public void mockLabPOSTErrorTest() {
        stubFor(post(getEndpoint()).withRequestBody(matchingJsonPath("$.contact.id", equalTo("666")))
                        .willReturn(aResponse()
                                            .withStatus(HttpStatus.SC_SERVICE_UNAVAILABLE)
                                            .withBody(getServerErrorResponseBody())
                                            .withHeader(getServerErrorHeaderKey(), getServerErrorHeaderValue())));

        SoftAssert softAssert = new SoftAssert();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(getUrl() + getEndpoint()))
                                          .POST(HttpRequest.BodyPublishers.ofFile(
                                                  Path.of(getRequestBodyPath() + getErrorRequestBody())))
                                          .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int actualStatusCode = response.statusCode();
            testResultsHead();
            log.info("Status code: {}", actualStatusCode);
            softAssert.assertEquals(actualStatusCode, HttpStatus.SC_SERVICE_UNAVAILABLE);

            String actualBody = response.body();
            log.info("Response body: {}", actualBody);
            softAssert.assertEquals(actualBody, getServerErrorResponseBody());

            String actualHeader = "";
            Optional<String> optional = response.headers().firstValue(getServerErrorHeaderKey());
            if (optional.isPresent()) {
                actualHeader = optional.get();
            }
            log.info("{}: {}", getServerErrorHeaderKey(), actualHeader);
            softAssert.assertEquals(actualHeader, getServerErrorHeaderValue());
            softAssert.assertAll();
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
