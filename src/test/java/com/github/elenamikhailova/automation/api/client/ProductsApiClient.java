package com.github.elenamikhailova.automation.api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import org.slf4j.*;


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
        log.info("Getting products from {}", PRODUCTS_LIST);
        return given()
                .spec(requestSpecification)
                .when()
                .get(PRODUCTS_LIST);
    }
}

