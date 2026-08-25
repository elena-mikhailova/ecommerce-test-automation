package com.github.elenamikhailova.automation.api.client;

import com.github.elenamikhailova.automation.api.model.CreateUserRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.*;


import static io.restassured.RestAssured.given;

public class UserApiClient {
    private static final String CREATE_ACCOUNT = "/createAccount";

    private final RequestSpecification requestSpecification;
    private static final Logger log =
            LoggerFactory.getLogger(UserApiClient.class);

    public UserApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    @Step("Create user account")
    public Response createUser(CreateUserRequest user) {
        log.info("Creating account via {}", CREATE_ACCOUNT);
        return given()
                .spec(requestSpecification)
                .formParam("name", user.getName())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getBirthDate())
                .formParam("birth_month", user.getBirthMonth())
                .formParam("birth_year", user.getBirthYear())
                .formParam("firstname", user.getFirstName())
                .formParam("lastname", user.getLastName())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress1())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipCode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobileNumber())
                .when()
                .post(CREATE_ACCOUNT);
    }
}

