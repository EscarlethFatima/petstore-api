package com.petstore.api;

import io.restassured.response.Response;

public class UserClient {
    private final RequestBuilder requestBuilder = new RequestBuilder();

    public Response createAnUser(Object user) {
        return requestBuilder.withBody(user)
                .build()
                .post("/user");
    }

    public Response getUserByUserName(String username) {
        return requestBuilder.withPathParam("username", username)
                .build()
                .get("/user/{username}");
    }

    public Response login(String username, String password) {
        return requestBuilder.withQueryParam("username", username)
                .withQueryParam("password", password)
                .build()
                .get("/user/login");
    }
}
