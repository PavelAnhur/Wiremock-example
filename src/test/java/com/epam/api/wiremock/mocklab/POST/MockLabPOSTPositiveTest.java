package com.epam.api.wiremock.mocklab.POST;

import com.epam.api.wiremock.dataprovider.DataProviderForPOSTPositiveTest;
import com.epam.api.wiremock.mocklab.POST.MockLabPOSTTestsConditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@Slf4j
public class MockLabPOSTPositiveTest extends MockLabPOSTTestsConditions {
    private final int statusCode;
    private final String requestBody;
    private final String responseBody;

    @Factory(dataProvider = "dataForPOSTTest", dataProviderClass = DataProviderForPOSTPositiveTest.class)
    public MockLabPOSTPositiveTest(int statusCode, String requestBody, String responseBody) {
        this.statusCode = statusCode;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    @Test
    public void mockLabPOSTQueryPositiveTest() {
        stubFor(post(getEndpoint()).withRequestBody(equalToJson(getRequestBody()))
                        .willReturn(aResponse()
                                            .withStatus(HttpStatus.SC_CREATED)
                                            .withBody(getResponseBody())
                                            .withHeader(getHeaderKey(), getHeaderValue())));

        SoftAssert softAssert = new SoftAssert();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(getUrl() + getEndpoint()))
                                      .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                                      .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int actualStatusCode = response.statusCode();
            log.info("Test -- {}, response status code: {}", this.getClass().getSimpleName(), actualStatusCode);
            softAssert.assertEquals(actualStatusCode, statusCode);
            String actualResponseBody = response.body();
            log.info("Test -- {}, response body: {}", this.getClass().getSimpleName(), actualResponseBody);
            softAssert.assertEquals(response.body(), responseBody);
            softAssert.assertAll();
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
