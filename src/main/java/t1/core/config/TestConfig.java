package t1.core.config;

import com.codeborne.selenide.Configuration;
import static t1.core.utils.Env.str;


public class TestConfig {
    public static void setupSelenide() {
        Configuration.baseUrl = str("baseUrl", "https://t1.ru");
        Configuration.browser = str("browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(str("headless", "true"));
        String remote = str("remoteUrl", "");
        Configuration.timeout = 15000;
        Configuration.pageLoadStrategy = "eager";
        if (!remote.isBlank()) {
            Configuration.remote = remote; // e.g. http://localhost:4444/wd/hub
            Configuration.browserSize = "1920x1080";
        }
    }
}
