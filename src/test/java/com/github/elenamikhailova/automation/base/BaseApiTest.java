package com.github.elenamikhailova.automation.base;

import com.github.elenamikhailova.automation.config.RequestSpecFactory;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseApiTest {

    protected RequestSpecification requestSpecification;

    @BeforeEach
    protected void setUpApi() {

        requestSpecification = RequestSpecFactory.createDefaultSpec();

    }
}
