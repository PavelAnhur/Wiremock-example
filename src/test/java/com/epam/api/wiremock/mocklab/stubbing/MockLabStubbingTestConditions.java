package com.epam.api.wiremock.mocklab.stubbing;

import com.epam.api.wiremock.mocklab.MockLabCommonConditions;

public class MockLabStubbingTestConditions extends MockLabCommonConditions {
    private static final String ENDPOINT = "/__admin/mappings/new";

    public static String getEndpoint() {
        return ENDPOINT;
    }
}
