package com.github.elenamikhailova.automation.ui.tests;

import com.github.elenamikhailova.automation.api.client.UserApiClient;
import com.github.elenamikhailova.automation.api.data.UserData;
import com.github.elenamikhailova.automation.api.model.CreateUserRequest;
import com.github.elenamikhailova.automation.base.BaseWebTest;
import com.github.elenamikhailova.automation.ui.components.Header;
import com.github.elenamikhailova.automation.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.open;

import com.github.elenamikhailova.automation.config.RequestSpecFactory;

public class LoginUiTest extends BaseWebTest {
    private final LoginPage objLoginPage = new LoginPage();
    private final UserData userData = new UserData();
    private final Header header = new Header();
    private CreateUserRequest user;
    private UserApiClient userApiClient;


    @BeforeEach
    void setUp() {
        userApiClient = new UserApiClient(RequestSpecFactory.createDefaultSpec());
    }

    @Test
    @DisplayName("User can log in with valid credentials")
    void canLoginWithValidCredentials() {
        user = userData.generateRandomUser();
        userApiClient.createUser(user);
        open("/login");
        objLoginPage.login(user.getEmail(), user.getPassword());
        header.getLoggedInUser()
                .shouldBe(visible)
                .shouldHave(text(user.getName()));
    }

    @AfterEach
    public void cleanUp() {
        if (user != null) {
            userApiClient.deleteUserAccount(user.getEmail(), user.getPassword());
        }
    }
}
