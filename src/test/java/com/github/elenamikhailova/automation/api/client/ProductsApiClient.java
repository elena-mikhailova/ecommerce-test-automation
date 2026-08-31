package com.github.elenamikhailova.automation.api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;


public class ProductsApiClient {

    private static final String PRODUCTS_LIST = "/productsList";
    private static final String SEARCH_PRODUCT = "/searchProduct";

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

    @Step("Search product")
    public Response searchProduct(String searchTerm) {
        log.info("Searching product via {}", SEARCH_PRODUCT);
        return given()
                .spec(requestSpecification)
                .formParam("search_product", searchTerm)
                .when()
                .post(SEARCH_PRODUCT);
    }
}

