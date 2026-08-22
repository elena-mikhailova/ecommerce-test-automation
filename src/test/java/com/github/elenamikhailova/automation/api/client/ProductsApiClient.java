package com.github.elenamikhailova.automation.api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductsApiClient {

    private static final String PRODUCTS_LIST = "/productsList";

    private final RequestSpecification requestSpecification;
    private static final Logger log =
            LoggerFactory.getLogger(ProductsApiClient.class);

    public ProductsApiClient(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    @Step("Get all products")
    public Response getAllProducts() {
        log.info("Sending GET request to {}", PRODUCTS_LIST);

        Response response = given()
                .spec(requestSpecification)
                .when()
                .get(PRODUCTS_LIST);

        log.info(
                "GET {} completed with HTTP status {}",
                PRODUCTS_LIST,
                response.statusCode()
        );

        return response;
    }
}

