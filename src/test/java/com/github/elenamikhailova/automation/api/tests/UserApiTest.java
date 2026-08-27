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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
        Response deleteResponse = userApiClient.deleteUserAccount(user.getEmail(), user.getPassword());
        deleteResponse.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("Account deleted!"));
        user = null;
    }

    @Test
    @DisplayName("POST /verifyLogin accepts valid credentials")
    void shouldVerifyLoginWithValidCredentials() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        Response response = userApiClient.verifyLogin(user.getEmail(), user.getPassword());
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("User exists!"));
    }

    @Test
    @DisplayName("POST /verifyLogin cannot accept invalid credentials")
    void cannotVerifyLoginWithInvalidCredentials() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        Response response = userApiClient.verifyLogin(user.getEmail(), "qwdockdok");
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(404))
                .body("message", equalTo("User not found!"));
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("POST /verifyLogin rejects invalid credentials")
    @MethodSource("com.github.elenamikhailova.automation.api.data.UserData#invalidLoginCases")
    void rejectsInvalidCredentials(String caseName,
                                   CreateUserRequest testUser,
                                   String email,
                                   String password,
                                   int expectedResponseCode,
                                   String expectedMessage) {
        user = testUser;
        userApiClient.createUser(user);
        Response response = userApiClient.verifyLogin(email, password);
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(expectedResponseCode))
                .body("message", equalTo(expectedMessage));
    }

    @Test
    @DisplayName("PUT /updateAccount updates the data of account")
    void canUpdateUser() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);

        CreateUserRequest updatedUser = user.toBuilder()
                .firstName("cpcpvp")
                .build();
        Response updatedResponse = userApiClient.updateAccount(updatedUser);
        updatedResponse.then()
                .body("responseCode", equalTo(200))
                .body("message", equalTo("User updated!"));
        Response getUserResponse = userApiClient.getUserByEmail(user.getEmail());
        getUserResponse.then()
                .body("user.first_name", equalTo("cpcpvp"));
    }


    @AfterEach
    public void tearDown() {
        if (user != null) {
            userApiClient.deleteUserAccount(user.getEmail(), user.getPassword());
        }
    }
}

