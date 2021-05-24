package com.epam.api.wiremock.mocklab.POST;

import com.epam.api.wiremock.mocklab.MockLabCommonConditions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockLabPOSTTestsConditions extends MockLabCommonConditions {
    private static final String REQUEST_BODY = "{\n  \"contact\": {\n      \"id\": \"7374859\",\n      \"firstName\": " +
                                                       "\"Wire\",\n      \"lastName\": \"Mock\",\n      \"email\":" +
                                                       " \"wiremock@gmail.com\",\n      \"dateAdded\": \"Jason st 8-23\"," +
                                                       "\n      \"companyId\": \"999999\"\n    }\n}";
    private static final String RESPONSE_BODY = "{\n  \"result\": \"success!\" \n}";
    private static final String ENDPOINT = "/v1/contacts";
    private static final String HEADER_KEY = "headers";
    private static final String HEADER_VALUE = "{ }";
    private static final String ERROR_REQUEST_BODY_JSON = "error-request-body.json";
    private static final String REQUEST_BODY_PATH = "src/test/resources/request-body/";
    private static final String SERVER_ERROR_RESPONSE_BODY = "The API is currently unavailable";
    private static final String SERVER_ERROR_HEADER_KEY = "Content-Type";
    private static final String SERVER_ERROR_HEADER_VALUE = "text/plain";

    public static String getServerErrorHeaderKey() {
        return SERVER_ERROR_HEADER_KEY;
    }

    public static String getServerErrorHeaderValue() {
        return SERVER_ERROR_HEADER_VALUE;
    }

    public static String getServerErrorResponseBody() {
        return SERVER_ERROR_RESPONSE_BODY;
    }

    public static String getRequestBodyPath() {
        return REQUEST_BODY_PATH;
    }

    public static String getErrorRequestBody() {
        return ERROR_REQUEST_BODY_JSON;
    }

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
