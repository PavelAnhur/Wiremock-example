package com.epam.api.wiremock.dataprovider;

import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;

public class DataProviderForGETTests {

    @DataProvider(name = "dataForGETTest")
    public Object[][] getTestData() {
        return new Object[][] {
                {1, HttpStatus.SC_OK},
                {666, HttpStatus.SC_OK},
                {0, HttpStatus.SC_OK}
        };
    }
}
