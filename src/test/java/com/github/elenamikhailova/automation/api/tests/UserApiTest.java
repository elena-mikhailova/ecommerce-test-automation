package com.github.elenamikhailova.automation.api.tests;

import com.github.elenamikhailova.automation.api.client.ProductsApiClient;
import com.github.elenamikhailova.automation.api.client.UserApiClient;
import com.github.elenamikhailova.automation.api.model.CreateUserRequest;
import com.github.elenamikhailova.automation.base.BaseApiTest;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;

public class UserApiTest extends BaseApiTest {
    private UserApiClient userApiClient;
    private Faker faker = new Faker();


    @BeforeEach
    void setUp() {
        userApiClient = new UserApiClient(requestSpecification);
    }

    @Test
    @DisplayName("POST /createAccount creates a new user")
    void canCreateUser() {
        CreateUserRequest user = generateRandomUser();
        Response response = userApiClient.createUser(user);
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(201))
                .body("message", equalTo("User created!"));
        userApiClient.deleteUserAccount(user);
    }

    @Test
    @DisplayName("GET /getUserDetailByEmail returns user details")
    void canGetUserByEmail() {
        CreateUserRequest user = generateRandomUser();
        userApiClient.createUser(user);
        Response getUserResponse = userApiClient.getUserByEmail(user.getEmail());
        getUserResponse.then()
                .statusCode(200)
                .body("user.email", equalTo(user.getEmail()));
        userApiClient.deleteUserAccount(user);
    }

    @Test
    @DisplayName("DELETE /deleteAccount deletes user")
    void canDeleteUser() {
        CreateUserRequest user = generateRandomUser();
        userApiClient.createUser(user);
        Response deleteResponse = userApiClient.deleteUserAccount(user);
        deleteResponse.then()
                .statusCode(200)
                .body("message", equalTo("Account deleted!"));
    }

    private CreateUserRequest generateRandomUser() {
        return CreateUserRequest.builder()
                .name(faker.name().firstName())
                .email("User_" + System.currentTimeMillis() + "@test.com")
                .password(faker.credentials().password())
                .title("Mrs")
                .birthDate(String.valueOf(faker.number().numberBetween(1, 29)))
                .birthMonth(String.valueOf(faker.number().numberBetween(1, 13)))
                .birthYear(String.valueOf(faker.number().numberBetween(1980, 1999)))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .company(faker.company().name())
                .address1(faker.address().streetAddress())
                .address2(faker.address().secondaryAddress())
                .country("Canada")
                .zipCode(faker.address().zipCode())
                .state(faker.address().state())
                .city(faker.address().city())
                .mobileNumber(faker.phoneNumber().cellPhone())
                .build();
    }
}

