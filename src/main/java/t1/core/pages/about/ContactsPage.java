package t1.core.pages.about;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ContactsPage {
    @Step("Страница «Контакты» открыта")
    public void shouldBeOpened() {
        $x("//main | //section").shouldBe(visible);
    }
}