package t1.core.drivers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class DriverFactory {

    public static void setup(String browser) {
        String remoteUrl = System.getProperty("remoteUrl");

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            setupRemoteChrome(remoteUrl);
        } else {
            setupLocalChrome(browser);
        }
    }

    private static void setupRemoteChrome(String remoteUrl) {
        Configuration.remote = remoteUrl;
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        options.addArguments(
                "--no-sandbox",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--lang=ru-RU"
        );

        Configuration.browserCapabilities = options;
    }

    private static void setupLocalChrome(String browser) {
        if (browser == null || browser.equals("chrome")) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";

            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NONE);

            options.addArguments(
                    "--disable-blink-features=AutomationControlled",
                    "--disable-infobars",
                    "--disable-notifications",
                    "--lang=ru-RU",
                    "--window-size=1920,1080"
            );

            options.setExperimentalOption(
                    "excludeSwitches",
                    List.of("enable-automation")
            );
            options.setExperimentalOption("useAutomationExtension", false);

            Configuration.browserCapabilities = options;
        }
    }
}