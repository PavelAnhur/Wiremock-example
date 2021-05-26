package com.epam.api.wiremock.dataprovider;

import com.epam.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

@Slf4j
public class DataProviderForStubbingTests {
    private static final String PATH = "src/test/resources/stubs/";
    private static final String REQUEST_BODY_JSON_POSITIVE =
            "create-new-company.json";
    private static final String REQUEST_BODY_JSON_NEGATIVE =
            "create-new-contact---error.json";

    @DataProvider(name = "dataForStubbingTest")
    public Object[][] getDataForTest() {
        return new Object[][]{
                {JsonUtils.fromJsonFileToString(PATH, REQUEST_BODY_JSON_POSITIVE)},
                {JsonUtils.fromJsonFileToString(PATH, REQUEST_BODY_JSON_NEGATIVE)},
        };
    }
}
