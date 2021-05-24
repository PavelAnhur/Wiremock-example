package com.epam.api.wiremock.mocklab.GET;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

@Slf4j
public class MockLabGETErrorTest extends MockLabGETTestsConditions {

    @Test
    public void mockLabGETQueryWithErrorTest() {
        stubFor(get(getEndpointErrorQuery())
                        .willReturn(aResponse()
                                            .withStatus(HttpStatus.SC_NOT_FOUND)
                                            .withBody(getResponseBodyErrorQuery())
                                            .withHeader(getHeaderKey(), getHeaderValueErrorQuery())));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(getUrl() + getEndpointErrorQuery()))
                                      .GET()
                                      .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND, "Invalid status code");
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }

        verify(exactly(1), getRequestedFor(urlEqualTo(getEndpointErrorQuery())));
        verify(getRequestedFor(urlEqualTo(getEndpointErrorQuery())).withoutHeader(getHeaderKey()));
    }
}
