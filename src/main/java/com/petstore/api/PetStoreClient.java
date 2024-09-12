package com.petstore.api;
import io.restassured.response.Response;

public class PetStoreClient {
    private final RequestBuilder requestBuilder = new RequestBuilder();

    public Response createPet(Object pet) {
        return requestBuilder.withBody(pet)
                .build()
                .post("/pet");
    }

    public Response getPetById(int petId) {
        return requestBuilder.withPathParam("petId", petId)
                .build()
                .get("/pet/{petId}");
    }

    public Response updatePet(Object pet) {
        return requestBuilder.withBody(pet)
                .build()
                .put("/pet");
    }

    public Response deletePet(int petId) {
        return requestBuilder.withPathParam("petId", petId)
                .build()
                .delete("/pet/{petId}");
    }
}
