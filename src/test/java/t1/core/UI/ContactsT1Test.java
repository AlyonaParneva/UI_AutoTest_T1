package t1.core.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.about.ContactsPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static t1.core.constans.UrlPages.ABOUT_URL;
import static t1.core.constans.UrlPages.CONTACTS_URL;

@Tag("CNT")
@DisplayName("Contacts. Контактная информация")
public class ContactsT1Test extends BaseTest {

    ContactsPage contacts = new ContactsPage();

    @Test
    @Tag("CNT-001")
    @DisplayName("CNT-001. Контакты: телефон, email, адрес")
    void cnt001_contactsVisible() {

        step("SRZ: Открыть страницу контактов", () -> {
            open(ABOUT_URL + CONTACTS_URL);
            contacts.shouldBeOpened();
        });

        step("SRZ: Контактные данные отображаются", () -> {
            contacts.contactsShouldBeVisible();
        });
    }

    @Test
    @Tag("CNT-002")
    @DisplayName("CNT-002. Ссылки tel:/mailto:")
    void cnt002_contactLinks() {

        step("SRZ: Открыть страницу контактов", () -> {
            open(ABOUT_URL + CONTACTS_URL);
            contacts.shouldBeOpened();
        });

        step("SRZ: Проверка ссылок", () -> {
            contacts.linksShouldBeCorrect();
        });
    }
}
