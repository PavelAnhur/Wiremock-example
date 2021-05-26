package com.epam.api.wiremock.program;

import com.epam.api.wiremock.common.CommonConditions;
import com.epam.configuration.AddressConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class GetQueryWithProgramMockConditions extends CommonConditions {
    private static final String END_POINT = "/api/friend";
    private WireMockServer wireMockServer;

    @BeforeMethod
    public void setUp() {
        AddressConfiguration.makePortAvailableIfOccupied(getPort());
        wireMockServer = new WireMockServer(options().bindAddress(getHost()).port(getPort()));
        wireMockServer.start();

        ResponseDefinitionBuilder mockResponse =
                new ResponseDefinitionBuilder()
                        .withStatus(HttpStatus.SC_OK)
                        .withStatusMessage("Hello, my friend!")
                        .withHeader("Content-Type", "applications/json");

        stubFor(get(END_POINT).willReturn(mockResponse));
    }

    @AfterSuite
    public void tearDown() {
        wireMockServer.stop();
    }

    public static String getEndPoint() {
        return END_POINT;
    }
}
