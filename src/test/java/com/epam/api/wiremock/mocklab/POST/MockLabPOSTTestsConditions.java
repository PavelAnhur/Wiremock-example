package com.epam.api.wiremock.mocklab.POST;

import com.epam.api.wiremock.mocklab.MockLabCommonConditions;

public class MockLabPOSTTestsConditions extends MockLabCommonConditions {
    private static final String REQUEST_BODY = "{\n  \"contact\": {\n      \"id\": \"7374859\",\n      \"firstName\": \"Wire\",\n      \"lastName\": \"Mock\",\n      \"email\": \"wiremock@gmail.com\",\n      \"dateAdded\": \"Jason st 8-23\",\n      \"companyId\": \"999999\"\n    }\n}";
    private static final String RESPONSE_BODY = "{\n  \"result\": \"success!\" \n}";
    private static final String ENDPOINT = "/v1/contacts";
    private static final String HEADER_KEY = "headers";
    private static final String HEADER_VALUE = "{ }";

    public static String getRequestBody() {
        return REQUEST_BODY;
    }

    public static String getResponseBody() {
        return RESPONSE_BODY;
    }

    public static String getHeaderKey() {
        return HEADER_KEY;
    }

    public static String getHeaderValue() {
        return HEADER_VALUE;
    }

    public static String getEndpoint() {
        return ENDPOINT;
    }
}
