package com.epam.api.wiremock.mocklab.GET;

import com.epam.api.wiremock.dataprovider.DataProviderForGETTests;
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
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

@Slf4j
public class MockLabGETTest extends MockLabGETTestsConditions {
    private final int contactNumber;
    private final int statusCode;

    @Factory(dataProvider = "dataForGETTest", dataProviderClass = DataProviderForGETTests.class)
    public MockLabGETTest(final int contactNumber,final int statusCode) {
        this.contactNumber = contactNumber;
        this.statusCode = statusCode;
    }

    @Test
    public void MockLabGETQueryTest() {
        stubFor(get(urlPathMatching(getEndpoint() + getRegex()))
                        .willReturn(aResponse()
                                            .withStatus(HttpStatus.SC_OK)
                                            .withBody(getResponseBody())
                                            .withHeader(getHeaderKey(), getHeaderValue())
                                            .withTransformers(getTransformer())));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(getUrl() + getEndpoint() + contactNumber))
                                      .GET()
                                      .build();
        SoftAssert softAssert = new SoftAssert();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            testResultsHead();
            int actualStatusCode = response.statusCode();
            log.info("Status code: {}", actualStatusCode);
            softAssert.assertEquals(actualStatusCode, statusCode);

            String actualResponseBody = response.body();
            log.info("Body: {}", actualResponseBody);
            softAssert.assertEquals(actualResponseBody, getResponseBody());

            String actualHeaderValue = "";
            Optional<String> optional = response.headers().firstValue(getHeaderKey());
            if (optional.isPresent()) {
                actualHeaderValue = optional.get();
            }
            log.info("{}: {}", getHeaderKey(), actualHeaderValue);
            softAssert.assertEquals(actualHeaderValue, getHeaderValue());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
        softAssert.assertAll("Something goes wrong!");
    }
}
