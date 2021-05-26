package com.epam.api.wiremock.mocklab;

import com.epam.api.wiremock.common.CommonConditions;
import com.epam.configuration.AddressConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public abstract class MockLabCommonConditions extends CommonConditions {
    private WireMockServer wireMockServer;

    @BeforeSuite
    public void setUpWireMockServer() {
        AddressConfiguration.makePortAvailableIfOccupied(getPort());
        wireMockServer = new WireMockServer(options().port(getPort()).bindAddress(getHost()));
        wireMockServer.start();
    }

    @AfterSuite
    public void tearDown() {
        wireMockServer.stop();
    }
}
