package t1.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import t1.core.config.TestConfig;
import t1.core.drivers.DriverFactory;
import t1.core.listeners.TestResultListener;

import static t1.core.utils.Env.str;

@ExtendWith(TestResultListener.class)
public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        DriverFactory.setupLocalDrivers(System.getProperty("browser"));
        setupSelenide();
    }

    public static void setupSelenide() {
        Configuration.baseUrl = str("baseUrl", "https://t1.ru");
        Configuration.browser = str("browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(str("headless", "true"));
        String remote = str("remoteUrl", "");
        Configuration.timeout = 15000;
        Configuration.pageLoadStrategy = "none";
        Configuration.pageLoadTimeout = 60000;
        if (!remote.isBlank()) {
            Configuration.remote = remote;
            Configuration.browserSize = "1920x1080";
        }
    }


    @AfterEach
    void tearDown() { Selenide.closeWebDriver(); }
}
