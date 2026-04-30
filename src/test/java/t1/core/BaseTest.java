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
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

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
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.pollingInterval = 300;

        Configuration.pageLoadStrategy = "normal";
        Configuration.clickViaJs = false;
        Configuration.fastSetValue = false;

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        options.addArguments("--disable-blink-features=AutomationControlled");

        options.addArguments(
                "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/147.0.0.0 Safari/537.36"
        );

        options.setExperimentalOption(
                "excludeSwitches",
                List.of("enable-automation")
        );

        options.setExperimentalOption(
                "useAutomationExtension",
                false
        );

        Configuration.browserCapabilities = options;
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
