package com.github.elenamikhailova.automation.api.tests;

import com.github.elenamikhailova.automation.api.client.ProductsApiClient;
import com.github.elenamikhailova.automation.api.data.ProductData;
import com.github.elenamikhailova.automation.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;


public class ProductsApiTest extends BaseApiTest {
    private ProductsApiClient productsApiClient;
    private ProductData productData = new ProductData();

    @BeforeEach
    void setUp() {
        productsApiClient = new ProductsApiClient(requestSpecification);
    }

    @Test
    @DisplayName("GET /productsList returns a non-empty product list")
    void canGetAllProducts() {
        Response response = productsApiClient.getAllProducts();
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("products", notNullValue())
                .body("products", not(empty()));
    }

    @Test
    @DisplayName("POST /searchProduct returns a product")
    void canSearchProducts() {
        String searchTerm = productData.generateSearchTerm();
        Response response = productsApiClient.searchProduct(searchTerm);
        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("products", not(empty()))
                .body("products.name", hasItem(containsStringIgnoringCase(searchTerm)));

    }
}
