package com.epam.api.wiremock.mocklab.GET;

import com.epam.api.wiremock.mocklab.MockLabCommonConditions;

public class MockLabGETTestsConditions extends MockLabCommonConditions {
    private static final String REGEX = "[0-9]{1,10}";
    private static final String ENDPOINT = "/v1/contacts/";
    private static final String REQUEST_BODY = "{\n  \"contact\": {\n      \"id\": \"{{{request.pathSegments.2}}}\",\n      \"firstName\": \"{{{randomValue length=6 type='ALPHANUMERIC'}}}\",\n      \"lastName\": \"{{{randomValue length=10 type='ALPHANUMERIC'}}}\",\n      \"email\": \"{{{randomValue length=12 type='ALPHANUMERIC'}}}@example.com\",\n      \"dateAdded\": \"{{{now offset='-3 months' format='yyyy-MM-dd'}}}\",\n      \"companyId\": \"123\"\n    }\n}\n\n";
    private static final String HEADER_KEY = "Content-Type";
    private static final String HEADER_VALUE = "application/json";
    private static final String TRANSFORMER = "response-template";

    public static String getRegex() {
        return REGEX;
    }

    public static String getEndpoint() {
        return ENDPOINT;
    }

    public static String getResponseBody() {
        return REQUEST_BODY;
    }

    public static String getHeaderKey() {
        return HEADER_KEY;
    }

    public static String getHeaderValue() {
        return HEADER_VALUE;
    }

    public static String getTransformer() {
        return TRANSFORMER;
    }
}
