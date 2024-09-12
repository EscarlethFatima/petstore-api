package com.petstore.api;

import io.restassured.response.Response;

public class StoreClient {
    private final RequestBuilder requestBuilder = new RequestBuilder();

    public Response placeAnOrder(Object order) {
        return requestBuilder.withBody(order)
                .build()
                .post("/store/order");
    }

    public Response getOrderById(int orderId) {
        return requestBuilder.withPathParam("orderId", orderId)
                .build()
                .get("/store/order/{orderId}");
    }

    public Response deleteOrderById(int orderId) {
        return requestBuilder.withPathParam("orderId", orderId)
                .build()
                .delete("/store/order/{orderId}");
    }
}
