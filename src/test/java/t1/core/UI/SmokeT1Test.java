package t1.core.UI;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static t1.core.constans.UrlPages.ABSOLUTE_URL;
import static t1.core.constans.UrlPages.T1_RU_BASE_URL;

import org.junit.jupiter.api.extension.ExtendWith;
import t1.core.BaseTest;
import t1.core.listeners.TestResultListener;
import t1.core.pages.main.MainPage;
import t1.core.pages.navigation.HeaderNavigation;


@Tag("SMOKE")
@DisplayName("SMOKE-001. Главная страница открывается и доступна навигация")
public class SmokeT1Test extends BaseTest {

    MainPage main;
    HeaderNavigation header;

    @BeforeEach
    void initPages() {
        main = new MainPage();
        header = new HeaderNavigation();
    }

    @Test
    void smoke001_mainPageAvailable() {
        step("SRZ: Открыть главную страницу T1", () -> {
            open(ABSOLUTE_URL);
            sleep(500);
            webdriver().driver().getWebDriver()
                    .navigate()
                    .to(T1_RU_BASE_URL);

            sleep(2000);
        });
        step("SRZ: Главная страница отображается", () -> {
            main.mainPageIsOpened();
        });
        step("SRZ: В хедере доступен пункт «Продукты и решения»", () -> {
            header.productsShouldBeVisible();
        });
    }
}
