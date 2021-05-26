package com.epam.api.wiremock.program;

import com.epam.api.wiremock.common.CommonConditions;
import com.epam.configuration.AddressConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class GetQueryWithProgramMockConditions extends CommonConditions {
    private static final String END_POINT = "/api/friend";
    private WireMockServer wireMockServer;
    private ResponseDefinitionBuilder mockResponse;

    @BeforeMethod
    public void setUp() {
        AddressConfiguration.makePortAvailableIfOccupied(getPort());
        wireMockServer = new WireMockServer(options().bindAddress(getHost()));
        wireMockServer.start();
        configureFor(getHost(), getPort());

        mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(HttpStatus.SC_OK);
        mockResponse.withStatusMessage("Hello, my friend!");
        mockResponse.withHeader("Content-Type", "applications/json");

        stubFor(get(END_POINT).willReturn(getMockResponse()));
    }

    @AfterSuite
    public void tearDown() {
        wireMockServer.stop();
    }

    public ResponseDefinitionBuilder getMockResponse() {
        return mockResponse;
    }

    public static String getEndPoint() {
        return END_POINT;
    }
}
