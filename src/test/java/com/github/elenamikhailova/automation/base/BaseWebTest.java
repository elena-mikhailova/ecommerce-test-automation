package com.github.elenamikhailova.automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.elenamikhailova.automation.annotation.FullRegression;
import com.github.elenamikhailova.automation.annotation.Ui;
import com.github.elenamikhailova.automation.config.TestConfig;
import com.github.elenamikhailova.automation.extension.TestExecutionWatcher;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@ExtendWith(TestExecutionWatcher.class)
@Ui
@FullRegression
public abstract class BaseWebTest {

    @BeforeAll
    static void setUpUi() {
        Configuration.baseUrl = TestConfig.getUiBaseUrl();
        // Use eager loading to avoid waiting for slow third-party resources
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    protected void tearDownUi() {
        closeWebDriver();
    }
}
