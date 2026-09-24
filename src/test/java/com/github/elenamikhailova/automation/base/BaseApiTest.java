package com.github.elenamikhailova.automation.base;

import com.github.elenamikhailova.automation.annotation.Api;
import com.github.elenamikhailova.automation.annotation.FullRegression;
import com.github.elenamikhailova.automation.config.RequestSpecFactory;
import com.github.elenamikhailova.automation.extension.TestExecutionWatcher;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(TestExecutionWatcher.class)
@Api
@FullRegression
public abstract class BaseApiTest {

    protected RequestSpecification requestSpecification;

    @BeforeEach
    protected void setUpApi() {
        requestSpecification = RequestSpecFactory.createDefaultSpec();
    }
}
