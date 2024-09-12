package com.petstore.api;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
public class RequestBuilder {
    private final RequestSpecification request;

    public RequestBuilder() {
        request = given().baseUri("https://petstore3.swagger.io/api/v3").contentType(ContentType.JSON);
    }

    public RequestBuilder withBody(Object body) {
        request.body(body).log();
        return this;
    }

    public RequestBuilder withPathParam(String key, Object value) {
        request.pathParam(key, value).log();
        return this;
    }

    public RequestBuilder withQueryParam(String key, Object value) {
        request.queryParam(key, value).log();
        return this;
    }

    public RequestSpecification build() {
        return request;
    }

}
