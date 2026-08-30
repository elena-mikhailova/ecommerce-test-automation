package com.github.elenamikhailova.automation.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.elenamikhailova.automation.config.TestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseWebTest {
    @BeforeAll
    static void setUpUi(){
        Configuration.baseUrl = TestConfig.getUiBaseUrl();
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }
}
