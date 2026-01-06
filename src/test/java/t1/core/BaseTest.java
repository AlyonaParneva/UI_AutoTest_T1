package t1.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import t1.core.config.TestConfig;
import t1.core.drivers.DriverFactory;
import t1.core.listeners.TestResultListener;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static t1.core.utils.Env.str;

@ExtendWith(TestResultListener.class)
public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        DriverFactory.setupLocalDrivers(System.getProperty("browser"));
        setupSelenide();
    }

    @BeforeEach
    void setUp() {
    }

    public static void setupSelenide() {
        Configuration.baseUrl = str("baseUrl", "https://t1.ru");
        Configuration.browser = str("browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(str("headless", "false"));

        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.pollingInterval = 300;

        Configuration.pageLoadStrategy = "none";
        Configuration.clickViaJs = false;
        Configuration.fastSetValue = false;

        String remote = str("remoteUrl", "");
        if (!remote.isBlank()) {
            Configuration.remote = remote;
            Configuration.browserSize = "1920x1080";
        }
    }

    @AfterAll
    static void tearDown() {
        Selenide.closeWebDriver();
    }
}
