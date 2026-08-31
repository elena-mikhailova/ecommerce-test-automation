package com.github.elenamikhailova.automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.elenamikhailova.automation.config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseWebTest {

    @BeforeAll
    static void setUpUi() {
        Configuration.baseUrl = TestConfig.getUiBaseUrl();
        // The demo site may wait too long for third-party resources
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterEach
    protected void tearDownUi() {
        closeWebDriver();
    }
}
