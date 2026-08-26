package com.github.elenamikhailova.automation.api.tests;

import com.github.elenamikhailova.automation.api.client.UserApiClient;
import com.github.elenamikhailova.automation.api.data.UserData;
import com.github.elenamikhailova.automation.api.model.CreateUserRequest;
import com.github.elenamikhailova.automation.base.BaseApiTest;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;

public class UserApiTest extends BaseApiTest {
    private UserApiClient userApiClient;
    private CreateUserRequest user;
    private UserData userData = new UserData();


    @BeforeEach
    void setUp() {
        userApiClient = new UserApiClient(requestSpecification);
    }

    @Test
    @DisplayName("POST /createAccount creates a new user")
    void canCreateUser() {
        user = userData.generateRandomUser();
        Response response = userApiClient.createUser(user);
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(201))
                .body("message", equalTo("User created!"));
    }

    @Test
    @DisplayName("GET /getUserDetailByEmail returns user details")
    void canGetUserByEmail() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        Response getUserResponse = userApiClient.getUserByEmail(user.getEmail());
        getUserResponse.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("user.email", equalTo(user.getEmail()));
    }

    @Test
    @DisplayName("DELETE /deleteAccount deletes user")
    void canDeleteUser() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        Response deleteResponse = userApiClient.deleteUserAccount(user);
        deleteResponse.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("Account deleted!"));
        user = null;
    }

    @AfterEach
    public void tearDown() {
        if (user != null) {
            userApiClient.deleteUserAccount(user);
        }
    }
}

