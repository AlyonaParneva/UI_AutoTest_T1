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
import t1.core.pages.navigation.HeaderNavigation;
import t1.core.pages.products.ProductsPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("NAV")
@DisplayName("NAV-001. Верхнее меню ведёт по разделам внутри t1.ru")
public class NavigationT1Test extends BaseTest {

    MainPage main = new MainPage();
    HeaderNavigation header = new HeaderNavigation();
    ProductsPage products = new ProductsPage();
    CasesPage casesPage = new CasesPage();
    AboutPage about = new AboutPage();
    ContactsPage contacts = new ContactsPage();

    @Test
    void nav001_headerNavigation() {

        step("SRZ: Открыть главную страницу T1", () -> {
            open("/");
            main.shouldBeOpened();
        });

        step("SRZ: В хедере доступен пункт «Продукты и решения»", () -> {
            header.productsShouldBeVisible();
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

        step("SRZ: Возврат на главную страницу выполнен", () -> {
            header.goToMainPage();
            main.shouldBeOpened();
        });

    }
}
