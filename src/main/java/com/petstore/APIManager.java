package com.petstore;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
public class APIManager {
    private APIManager() {
    }

    public static Response getResponse(Response response) {

        response.then()
                .log().status()
                .log().body();
        return response;
    }

    private static RequestSpecification getRequest(RequestSpecification requestSpecification) {
        return requestSpecification
                .log().method()
                .log().uri()
                .log().params()
                .log().body();
    }

    public static RequestSpecification getRequestSpecification() {
        return getRequest(new RequestSpecBuilder()
                .setBaseUri("https://petstore3.swagger.io/api/v3m")
                .setRelaxedHTTPSValidation()
                .build());
    }
}
