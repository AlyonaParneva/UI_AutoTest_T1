package t1.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import t1.core.config.TestConfig;
import t1.core.database.service.AnalyticsReportGenerator;
import t1.core.drivers.DriverFactory;
import t1.core.listeners.TestResultListener;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static t1.core.constans.UrlPages.ABSOLUTE_URL;
import static t1.core.constans.UrlPages.T1_RU_BASE_URL;
import static t1.core.utils.Env.str;

@ExtendWith(TestResultListener.class)
public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );

        DriverFactory.setup(System.getProperty("browser"));
        setupSelenideCommon();
    }

    @BeforeEach
    void openMainIfNeeded() {
        step("SRZ: Открыть главную страницу T1", () -> {
            open(T1_RU_BASE_URL);
            $("body").shouldBe(visible);
            sleep(3000);
            System.out.println("Current URL: " + WebDriverRunner.url());
        });
    }


    private static void setupSelenideCommon() {
        Configuration.baseUrl = str("baseUrl", T1_RU_BASE_URL);

        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 120000;
        Configuration.pollingInterval = 300;

        Configuration.pageLoadStrategy = "normal";
        Configuration.clickViaJs = false;
        Configuration.fastSetValue = false;
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @AfterAll
    static void afterAll() {
        AnalyticsReportGenerator.generate();
    }

}
