package t1.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import t1.core.config.TestConfig;
import t1.core.drivers.DriverFactory;
import t1.core.listeners.TestResultListener;

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
        DriverFactory.setup(System.getProperty("browser"));
        setupSelenideCommon();
    }

    @BeforeEach
    void openMainIfNeeded() {
        step("SRZ: Открыть главную страницу T1", () -> {
            open(ABSOLUTE_URL);
            sleep(500);
            webdriver().driver().getWebDriver()
                    .navigate()
                    .to(T1_RU_BASE_URL);

            sleep(2000);
        });
    }


    private static void setupSelenideCommon() {
        Configuration.baseUrl = str("baseUrl", T1_RU_BASE_URL);

        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.pollingInterval = 300;

        Configuration.pageLoadStrategy = "none";
        Configuration.clickViaJs = false;
        Configuration.fastSetValue = false;
    }

    @AfterAll
    static void tearDown() {
        Selenide.closeWebDriver();
    }
}
