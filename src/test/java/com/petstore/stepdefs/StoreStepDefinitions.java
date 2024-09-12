package com.petstore.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.api.ResponseValidator;
import com.petstore.api.StoreClient;
import com.petstore.model.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class StoreStepDefinitions {
    private StoreClient storeClient = new StoreClient();
    private Response response;
    private Order order;

    @Given("A new order is placed with the following details")
    public void a_pet_with_the_following_details(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        order = objectMapper.readValue(jsonString, Order.class);
    }

    @When("I submit the order")
    public void i_submit_the_order() {
        response = storeClient.placeAnOrder(order);
    }

    @Then("I verify the order is successfully created with ID {int}")
    public void i_verify_the_order_is_successfully_created_with_id(int orderId) {
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonPath(response, "id", orderId);
    }

    @When("I retrieve the order by ID {int}")
    public void i_retrieve_the_order_by_id(int orderId) {
        response = storeClient.getOrderById(orderId);
    }

    @Then("The retrieved order details should match the submitted order")
    public void the_retrieved_order_details_should_match_the_submitted_order() {
        ResponseValidator.validateJsonPath(response, "id", order.getId());
        ResponseValidator.validateJsonPath(response, "petId", order.getPetId());
        ResponseValidator.validateJsonPath(response, "quantity", order.getQuantity());
        ResponseValidator.validateJsonPath(response, "status", order.getStatus());
    }


    @Then("I delete the purchase with id {int}")
    public void i_delete_the_purchase_with_id(int orderId) {
        response = storeClient.deleteOrderById(orderId);
    }

    @Then("The response status code should be {int}")
    public void the_response_status_code_is(int statusCode) {
        ResponseValidator.validateStatusCode(response, statusCode);
    }
}
