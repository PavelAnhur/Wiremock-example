package com.epam.api.wiremock.local;

import com.epam.api.wiremock.common.CommonConditions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetQueryWithLocalMockConditions extends CommonConditions {
    private static final String FIRST_ENDPOINT = "/api/user/1";
    private static final String BODY_MESSAGE = "Hello, user number one!";

    public static String getEndpoint() {
        return FIRST_ENDPOINT;
    }

    public static String getBodyMessage() {
        return BODY_MESSAGE;
    }
}
