package t1.core.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.main.MainPage;
import t1.core.pages.main.CookiesBanner;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static t1.core.constans.UrlPages.SLASH_URL;

public class MainT1Test extends BaseTest {

    MainPage main = new MainPage();
    CookiesBanner cookies = new CookiesBanner();

    @Test
    @Tag("MAIN-001")
    @DisplayName("MAIN-001. Ключевые блоки и CTA «Все решения»")
    void main001_keyBlocksAndCta() {

        step("SRZ: Открыть главную страницу", () -> {
            open(SLASH_URL);
            main.shouldBeOpened();
        });

        step("SRZ: Страница загружена", () -> {
            main.shouldBeOpened();
        });

        step("SRZ: Кнопка «Продукты и решения» отображается", () -> {
            main.productsButtonShouldBeVisible();
        });

        step("SRZ: На странице есть карточки решений", () -> {
            assertThat(main.getCardsCount())
                    .as("Количество карточек на главной")
                    .isGreaterThan(0);
        });

        step("SRZ: Переход по CTA «Продукты и решения»", () -> {
            main.clickProductsButton();
        });
    }

    @Test
    @Tag("MAIN-002")
    @DisplayName("MAIN-002. Cookies-баннер: принятие и сохранение")
    void main002_cookiesBanner() {

        step("SRZ: Открыть главную страницу", () -> {
            open(SLASH_URL);
        });

        step("SRZ: Принять cookies, если баннер отображается", () -> {
            cookies.waitAndAcceptIfAppears();
        });

        step("SRZ: Обновить страницу", () -> {
            refresh();
        });

        step("SRZ: Cookies-баннер не отображается повторно", () -> {
            cookies.shouldNotBeVisible();
        });
    }
}
