package t1.core.drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class DriverFactory {

    public static void setupLocalDrivers(String browser) {
        if (browser == null) browser = "chrome";

        switch (browser) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "edge" -> WebDriverManager.edgedriver().setup();
            default -> setupChrome();
        }
    }

    private static void setupChrome() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        options.addArguments(
                "--disable-blink-features=AutomationControlled",
                "--disable-infobars",
                "--disable-notifications",
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--start-maximized",
                "--lang=ru-RU",
                "--window-size=1920,1080"
        );

        options.setExperimentalOption(
                "excludeSwitches",
                List.of("enable-automation")
        );
        options.setExperimentalOption("useAutomationExtension", false);

        ChromeDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
    }

}
