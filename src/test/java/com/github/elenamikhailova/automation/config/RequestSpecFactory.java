package com.github.elenamikhailova.automation.config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;


public final class RequestSpecFactory {

    // Automation Exercise returns JSON responses with text/html content type.
    static {
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    private RequestSpecFactory() {
    }

    public static RequestSpecification createDefaultSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri(TestConfig.getApiBaseUrl())
                .addFilter(new AllureRestAssured());

        return requestSpecBuilder.build();
    }

}
