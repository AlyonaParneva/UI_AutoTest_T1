package t1.core.UI;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.about.AboutPage;
import t1.core.pages.about.ContactsPage;
import t1.core.pages.cases.CasesPage;
import t1.core.pages.main.MainPage;
import t1.core.pages.navigation.FooterNavigation;
import t1.core.pages.navigation.HeaderNavigation;
import t1.core.pages.products.ProductsPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Epic("Навигация")
@Feature("Навигация по сайту t1.ru")
public class NavigationT1Test extends BaseTest {

    MainPage main = new MainPage();
    HeaderNavigation header = new HeaderNavigation();
    ProductsPage products = new ProductsPage();
    CasesPage casesPage = new CasesPage();
    AboutPage about = new AboutPage();
    ContactsPage contacts = new ContactsPage();
    FooterNavigation footer = new FooterNavigation();

    @Test
    @Tag("NAV-001")
    @DisplayName("NAV-001. Верхнее меню ведёт по разделам внутри t1.ru")
    void nav001_headerNavigation() {

        step("SRZ: Открыть главную страницу T1", () -> {
             open("/");
            main.shouldBeOpened();
        });

        step("SRZ: Переход в «Продукты и решения» выполнен", () -> {
            header.clickProducts();
            products.shouldBeOpened();
        });

        step("SRZ: Переход в «Кейсы» выполнен", () -> {
            header.clickCases();
            casesPage.shouldBeOpened();
        });

        step("SRZ: Переход в «О нас» выполнен", () -> {
            header.clickAbout();
            about.shouldBeOpened();
        });

        step("SRZ: Переход в «Контакты» выполнен", () -> {
            header.clickContacts();
            contacts.shouldBeOpened();
        });
    }

    @Test
    @Tag("NAV-002")
    @DisplayName("NAV-002. Хлебные крошки / возврат назад с сохранением контекста")
    void nav002_breadcrumbsBack() {

        step("SRZ: Открыть страницу «Продукты и решения»", () -> {
            open("/products");
            products.shouldBeOpened();
        });

        step("SRZ: Открыть первую карточку продукта", () -> {
            products.openFirstProductCard();
        });

        step("SRZ: Возврат назад в браузере", () -> {
            back();
            products.shouldBeOpened();
        });
    }

    @Test
    @Tag("NAV-003")
    @DisplayName("NAV-003. Футер: внутренние и внешние ссылки")
    void nav003_footerLinks() {
        step("SRZ: Открыть главную страницу", () -> {
            open("/");
            main.shouldBeOpened();
        });
        step("SRZ: Футер отображается", () -> {
            footer.footerShouldBeVisible();
        });
        step("SRZ: Внутренние ссылки футера работают", () -> {
            footer.clickProducts();
            products.shouldBeOpened();
            back();
            footer.clickCases();
            casesPage.shouldBeOpened();
            back();
            footer.clickAbout();
            about.shouldBeOpened();
        });
        step("SRZ: Внешние ссылки футера открываются", () -> {
            footer.clickTelegram();
            footer.clickVk();
            footer.clickHabr();
            footer.clickVc();
        });
    }


    @Test
    @Tag("NAV-004")
    @DisplayName("NAV-004. Переход на главную страницу по логотипу")
    void nav004_logoNavigation() {

        step("SRZ: Открыть любую внутреннюю страницу", () -> {
            open("/about");
            about.shouldBeOpened();
        });

        step("SRZ: Клик по логотипу T1", () -> {
            header.goToMainPage();
        });

        step("SRZ: Главная страница открыта", () -> {
            main.shouldBeOpened();
        });
    }
}
