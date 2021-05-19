package com.epam.api.wiremock.config;

import com.epam.configuration.AddressConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class CommonConditions {
    private WireMockServer wireMockServer;

    @BeforeSuite
    public void setUp() {
        AddressConfiguration.makePortAvailableIfOccupied(8080);
        wireMockServer = new WireMockServer(options().bindAddress("127.0.0.1"));
        wireMockServer.start();
    }

    @AfterSuite
    public void tearDown() {
        wireMockServer.stop();
    }
}
