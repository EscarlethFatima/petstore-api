package com.petstore.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.api.PetStoreClient;
import com.petstore.api.ResponseValidator;
import com.petstore.model.Pet;
import io.cucumber.java.en.*;

import io.restassured.response.Response;

public class PetStoreStepDefinitions {

    private PetStoreClient petstoreClient = new PetStoreClient();
    private Response response;
    private Pet pet;

    @Given("A pet with the following details")
    public void a_pet_with_the_following_details(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        pet = objectMapper.readValue(jsonString, Pet.class);
    }

    @When("I add the new pet to the store")
    public void i_create_the_pet() {
        response = petstoreClient.createPet(pet);
    }

    @Then("The pet is created successfully")
    public void the_pet_is_created_successfully() {
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Then("The pet is updated successfully")
    public void the_pet_is_updated_successfully() {
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Then("The pet is deleted successfully")
    public void the_pet_is_deleted_successfully() {
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Then("The response status code is {int}")
    public void the_response_status_code_is(int statusCode) {
        ResponseValidator.validateStatusCode(response, statusCode);
    }

    @When("I update the pet with the following details")
    public void i_update_the_pet_with_id_with_the_following_details(String petJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        pet = objectMapper.readValue(petJson, Pet.class);
        response = petstoreClient.updatePet(pet);
    }

    @When("I delete the pet with id {int}")
    public void i_delete_the_pet_with_id(int id) {
        response = petstoreClient.deletePet(id);
    }

    @When("I retrieve the pet by id {int}")
    public void i_retrieve_the_pet_by_id(int id) {
        response = petstoreClient.getPetById(id);
    }

    @Then("The pet name is {string}")
    public void the_pet_name_is(String expectedName) {
        ResponseValidator.validateJsonPath(response, "name", expectedName);
    }

    @Then("The pet status is {string}")
    public void the_pet_status_is(String expectedStatus) {
        ResponseValidator.validateJsonPath(response, "status", expectedStatus);
    }

    @Then("The pet tag name is {string}")
    public void the_pet_tag_name_is(String expectedTagName) {
        ResponseValidator.validateJsonPath(response, "tags[0].name", expectedTagName);
    }

    @Then("the pet is not found when retrieving by id {int}")
    public void the_pet_is_not_found_when_retrieving_by_id(int id) {
        response = petstoreClient.getPetById(id);
        ResponseValidator.validateStatusCode(response, 404);
    }
}
