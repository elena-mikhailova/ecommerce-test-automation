package com.github.elenamikhailova.automation.tests.ui;

import com.github.elenamikhailova.automation.api.client.UserApiClient;
import com.github.elenamikhailova.automation.api.model.request.CreateUserRequest;
import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.config.RequestSpecFactory;
import com.github.elenamikhailova.automation.data.factory.UserFactory;
import com.github.elenamikhailova.automation.ui.component.Header;
import com.github.elenamikhailova.automation.ui.page.LoginPage;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginUiTest extends BaseWebTest {
    private final LoginPage loginPage = new LoginPage();
    private final Header header = new Header();
    private CreateUserRequest user;
    private UserApiClient userApiClient;
    private final UserFactory userFactory = new UserFactory();


    @BeforeEach
    void setUp() {
        userApiClient = new UserApiClient(RequestSpecFactory.createDefaultSpec());
    }

    @Test
    @DisplayName("User can log in with valid credentials")
    void canLoginWithValidCredentials() {
        CreateUserRequest newUser = userFactory.validUser();
        Response createUserResponse = userApiClient.createUser(newUser);
        createUserResponse.then()
                .statusCode(200);
        assertThat(createUserResponse.jsonPath().getInt("responseCode"))
                .isEqualTo(201);
        user = newUser;
        loginPage.openPage();
        loginPage.login(user.getEmail(), user.getPassword());
        header.getLoggedInUser()
                .shouldBe(visible)
                .shouldHave(text(user.getName()));
    }

    @Test
    @DisplayName("User cannot log in with invalid credentials")
    void cannotLoginWithInvalidCredentials() {
        CreateUserRequest invalidUser = userFactory.validUser();
        loginPage.openPage();
        loginPage.login(invalidUser.getEmail(), invalidUser.getPassword());
        loginPage.getErrorMessage()
                .shouldBe(visible)
                .shouldHave(exactText("Your email or password is incorrect!"));

    }

    @AfterEach
    void cleanUp() {
        if (user != null) {
            userApiClient.deleteUserAccount(user.getEmail(), user.getPassword());
        }
    }
}
