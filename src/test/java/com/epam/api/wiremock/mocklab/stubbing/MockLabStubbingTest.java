package com.epam.api.wiremock.mocklab.stubbing;

import com.epam.api.wiremock.dataprovider.DataProviderForStubbingTests;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@Slf4j
public class MockLabStubbingTest extends MockLabStubbingTestConditions {
    private final String stubbingJson;

    @Factory(dataProvider = "dataForStubbingTest", dataProviderClass = DataProviderForStubbingTests.class)
    public MockLabStubbingTest(final String stubbingJson) {
        this.stubbingJson = stubbingJson;
    }

    @Test
    public void mockLabStubbingTest() {
        stubFor(post(urlEqualTo(getEndpoint())).withRequestBody(equalToJson(stubbingJson))
                        .willReturn(aResponse().withStatus(HttpStatus.SC_CREATED)));
    }
}
