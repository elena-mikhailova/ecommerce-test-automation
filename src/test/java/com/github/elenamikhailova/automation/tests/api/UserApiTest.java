package com.github.elenamikhailova.automation.tests.api;

import com.github.elenamikhailova.automation.api.client.UserApiClient;
import com.github.elenamikhailova.automation.api.model.request.CreateUserRequest;
import com.github.elenamikhailova.automation.api.model.response.UserDetailsResponse;
import com.github.elenamikhailova.automation.base.BaseApiTest;
import com.github.elenamikhailova.automation.data.UserData;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiTest extends BaseApiTest {
    private UserApiClient userApiClient;
    private CreateUserRequest user;
    private final UserData userData = new UserData();


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
                .statusCode(200);

        int responseCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");
        assertThat(responseCode)
                .isEqualTo(201);
        assertThat(message)
                .isEqualTo("User created!");

    }

    @Test
    @DisplayName("GET /getUserDetailByEmail returns user details")
    void canGetUserByEmail() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        Response getUserResponse = userApiClient.getUserByEmail(user.getEmail());
        getUserResponse.then()
                .statusCode(200);

        UserDetailsResponse body =
                getUserResponse.as(UserDetailsResponse.class);
        assertThat(body.responseCode())
                .isEqualTo(200);
        assertThat(body.user().email())
                .isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("DELETE /deleteAccount deletes user")
    void canDeleteUser() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        Response deleteResponse = userApiClient.deleteUserAccount(user.getEmail(), user.getPassword());
        deleteResponse.then()
                .statusCode(200);

        int responseCode = deleteResponse.jsonPath().getInt("responseCode");
        String message = deleteResponse.jsonPath().getString("message");
        assertThat(responseCode)
                .isEqualTo(200);
        assertThat(message)
                .isEqualTo("Account deleted!");
        // Prevent @AfterEach from deleting the same account again
        user = null;
    }

    @Test
    @DisplayName("POST /verifyLogin accepts valid credentials")
    void shouldVerifyLoginWithValidCredentials() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        Response response = userApiClient.verifyLogin(user.getEmail(), user.getPassword());
        response.then()
                .statusCode(200);
        int responseCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");
        assertThat(responseCode)
                .isEqualTo(200);
        assertThat(message)
                .isEqualTo("User exists!");
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("POST /verifyLogin rejects invalid credentials")
    @MethodSource("com.github.elenamikhailova.automation.data.UserData#invalidLoginCases")
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
                .statusCode(200);
        int responseCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");
        assertThat(responseCode)
                .isEqualTo(expectedResponseCode);
        assertThat(message)
                .isEqualTo(expectedMessage);
    }

    @Test
    @DisplayName("PUT /updateAccount updates the data of account")
    void canUpdateUser() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        String updatedFirstName = userData.generateFirstName();
        CreateUserRequest updatedUser = user.toBuilder()
                .firstName(updatedFirstName)
                .build();
        Response updatedResponse = userApiClient.updateAccount(updatedUser);
        updatedResponse.then()
                .statusCode(200);
        int responseCode = updatedResponse.jsonPath().getInt("responseCode");
        String message = updatedResponse.jsonPath().getString("message");
        assertThat(responseCode)
                .isEqualTo(200);
        assertThat(message)
                .isEqualTo("User updated!");
        Response getUserResponse = userApiClient.getUserByEmail(user.getEmail());
        getUserResponse.then()
                .statusCode(200);
        UserDetailsResponse body =
                getUserResponse.as(UserDetailsResponse.class);
        assertThat(body.user().firstName())
                .isEqualTo(updatedFirstName);
        assertThat(body.responseCode())
                .isEqualTo(200);
    }


    @AfterEach
    void cleanUp() {
        if (user != null) {
            userApiClient.deleteUserAccount(user.getEmail(), user.getPassword());
        }
    }
}

