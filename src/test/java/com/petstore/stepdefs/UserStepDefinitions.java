package com.petstore.stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.api.ResponseValidator;
import com.petstore.api.UserClient;
import com.petstore.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserStepDefinitions {

    private final UserClient userClient = new UserClient();
    private Response response;
    private User user;


    @Given("A new user with the following details")
    public void a_new_user_with_the_following_details(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        user = objectMapper.readValue(jsonString, User.class);
    }

    @When("I create the user")
    public void i_create_the_user() {
        response = userClient.createAnUser(user);
    }

    @Then("I verify the user was successfully created")
    public void i_verify_the_user_was_successfully_created() {
        ResponseValidator.validateStatusCode(response, 200);
    }

    @When("I retrieve the user by username {string}")
    public void i_retrieve_the_user_by_username(String username) {
        response = userClient.getUserByUserName(username);
    }

    @Then("I verify the user information is retrieved")
    public void i_verify_the_user_information_is_retrieved() {
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonPath(response, "username", user.getUsername());
        ResponseValidator.validateJsonPath(response, "firstName", user.getFirstName());
        ResponseValidator.validateJsonPath(response, "lastName", user.getLastName());
        ResponseValidator.validateJsonPath(response, "email", user.getEmail());
    }

    @When("I login the user")
    public void i_login_the_user() {
        response = userClient.login(user.getUsername(), user.getPassword());
    }

    @Then("I verify the user was successfully logged in")
    public void i_verify_the_user_was_successfully_logged_in() {
        ResponseValidator.validateStatusCode(response, 200);
    }
}
