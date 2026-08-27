package com.github.elenamikhailova.automation.api.client;

import com.github.elenamikhailova.automation.api.model.CreateUserRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.*;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    private static final String CREATE_ACCOUNT = "/createAccount";
    private static final String GET_USER_BY_EMAIL = "/getUserDetailByEmail";
    private static final String DELETE_USER_ACCOUNT = "/deleteAccount";
    private static final String VERIFY_LOGIN = "/verifyLogin";
    private static final String UPDATE_ACCOUNT = "/updateAccount";

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

    @Step("Get user by email")
    public Response getUserByEmail(String email) {
        log.info("Getting user via {}", GET_USER_BY_EMAIL);
        return given()
                .spec(requestSpecification)
                .queryParam("email", email)
                .when()
                .get(GET_USER_BY_EMAIL);
    }

    @Step("Delete user account")
    public Response deleteUserAccount(String email, String password) {
        log.info("Deleting user account {}", DELETE_USER_ACCOUNT);
        return given()
                .spec(requestSpecification)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete(DELETE_USER_ACCOUNT);
    }

    @Step("Verify login")
    public Response verifyLogin(String email, String password) {
        log.info("Verifying user login via {}", VERIFY_LOGIN);
        RequestSpecification request = given()
                .spec(requestSpecification);
        if (email != null) {
            request.formParam("email", email);
        }
        if (password != null) {
            request.formParam("password", password);
        }
        return request
                .when()
                .post(VERIFY_LOGIN);
    }

    @Step("Update user account")
    public Response updateAccount(CreateUserRequest user) {
        log.info("Updating account via {}", UPDATE_ACCOUNT);
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
                .put(UPDATE_ACCOUNT);
    }
}

