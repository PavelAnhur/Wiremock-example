package com.epam.api.wiremock.dataprovider;

import com.epam.utils.FileUtils;
import com.epam.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;

import java.io.IOException;

@Slf4j
public class DataProviderForPOSTPositiveTest {
    private static final String REQUEST_BODY_PATH = "src/test/resources/request-body/";
    private static final String RESPONSE_BODY_PATH = "src/test/resources/response-body/";
    private static final String REQUEST_BODY_JSON_VALID = "company-contact-valid.json";
    private static final String RESPONSE_BODY_VALID = "{\n  \"result\": \"success!\" \n}";
    private static final String REQUEST_BODY_JSON_INVALID = "company-contact-invalid.json";
    private static final String RESPONSE_BODY_FILE_INVALID = "response-body-invalid.txt";

    @DataProvider(name = "dataForPOSTTest")
    public Object[][] getDataForTest() {
        return new Object[][]{
                {HttpStatus.SC_CREATED, getBodyAsString(REQUEST_BODY_JSON_VALID), RESPONSE_BODY_VALID},
                {HttpStatus.SC_NOT_FOUND, getBodyAsString(REQUEST_BODY_JSON_INVALID), getResponseBodyAsString()}
        };
    }

    private String getBodyAsString(final String fileName) {
        try {
            return JsonUtils.fromJsonFileToString(REQUEST_BODY_PATH, fileName);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private String getResponseBodyAsString() {
        return FileUtils.getDataFromFile(RESPONSE_BODY_PATH, DataProviderForPOSTPositiveTest.RESPONSE_BODY_FILE_INVALID);
    }
}
