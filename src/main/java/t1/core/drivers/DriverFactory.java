package t1.core.drivers;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    public static void setupLocalDrivers(String browser) {
        if (browser == null) browser = "chrome";
        switch (browser) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "edge" -> WebDriverManager.edgedriver().setup();
            default -> WebDriverManager.chromedriver().setup();
        }
    }
}
