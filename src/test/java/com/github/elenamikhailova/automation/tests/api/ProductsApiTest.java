package com.github.elenamikhailova.automation.tests.api;

import com.github.elenamikhailova.automation.api.client.ProductsApiClient;
import com.github.elenamikhailova.automation.api.model.response.ProductResponse;
import com.github.elenamikhailova.automation.api.model.response.ProductsResponse;
import com.github.elenamikhailova.automation.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductsApiTest extends BaseApiTest {
    private ProductsApiClient productsApiClient;

    @BeforeEach
    void setUp() {
        productsApiClient = new ProductsApiClient(requestSpecification);
    }

    @Test
    @DisplayName("GET /productsList returns a non-empty product list")
    void canGetAllProducts() {
        Response response = productsApiClient.getAllProducts();
        response.then()
                .statusCode(200);
        ProductsResponse body = response.as(ProductsResponse.class);
        assertThat(body.responseCode())
                .isEqualTo(200);
        assertThat(body.products())
                .isNotNull()
                .isNotEmpty();
    }

    @ParameterizedTest(name = "Search term: {0}")
    @DisplayName("POST /searchProduct returns matching products")
    @MethodSource("com.github.elenamikhailova.automation.data.ProductData#searchTerms")
    void canSearchProducts(String searchTerm) {
        Response response = productsApiClient.searchProduct(searchTerm);
        response.then()
                .statusCode(200);
        ProductsResponse body = response.as(ProductsResponse.class);
        assertThat(body.responseCode())
                .isEqualTo(200);
        assertThat(body.products())
                .isNotNull()
                .isNotEmpty();
        assertThat(body.products())
                .extracting(ProductResponse::name)
                .anySatisfy(name ->
                        assertThat(name).containsIgnoringCase(searchTerm));
    }
}
