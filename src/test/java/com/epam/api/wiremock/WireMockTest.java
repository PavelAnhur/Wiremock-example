package com.epam.api.wiremock;

import com.epam.api.wiremock.config.CommonConditions;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@Slf4j
public class WireMockTest extends CommonConditions {

    private static final String TEST_URL = "http://localhost:8080/api/user/1";

    @Test
    public void wireMockTest() {
        configureFor("localhost", 8080);
        stubFor(WireMock.get(
                urlEqualTo(TEST_URL))
                        .willReturn(aResponse().withBody("Hello, user number one!").withStatus(200)));
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder(URI.create(TEST_URL))
//                                      .GET()
//                                      .build();
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not correct!");
//        } catch (IOException | InterruptedException e) {
//            log.error(e.getMessage());
//        }
    }
}
