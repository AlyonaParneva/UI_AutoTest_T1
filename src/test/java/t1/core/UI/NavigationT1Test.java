package t1.core.UI;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.pages.main.MainPage;
import t1.core.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("autotest")
@Epic("T1")
@Feature("Навигация")
@Story("Главная страница")
public class NavigationT1Test extends BaseTest {

    private final MainPage mainPage = new MainPage();

    @Test
    @DisplayName("NAV-001. Верхнее меню ведёт по разделам внутри t1.ru")
    void nav001HeaderNavigationTest() {
//        Allure.step("Открыть главную страницу T1", () -> {
//            open("/");
//        });
//        step("SRZ: Главная страница открыта", () -> {
//            mainPage.titleShouldBeVisible();
//        });
//        step("SRZ: Получаем текст заголовка главной страницы", () -> {
//            String title = mainPage.getTitleText();
//            assertThat(title).contains("Т1");
//        });
        step("SRZ: Переходим в раздел «Продукты и решения»", () -> {
            mainPage.clickProductsButton();
        });
        step("SRZ: Возвращаемся на главную страницу через логотип", () -> {
            mainPage.clickLogo();
            mainPage.titleShouldBeVisible();
        });
    }
}
