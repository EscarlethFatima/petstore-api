package com.petstore.api;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
public class ResponseValidator {
    public static void validateStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    public static void validateJsonPath(Response response, String jsonPath, Object expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    public static void validateFieldExists(Response response, String jsonPath) {
        response.then().body(jsonPath, notNullValue());
    }
}
