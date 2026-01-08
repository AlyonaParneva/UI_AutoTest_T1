package t1.core.pages.cases;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CasesPage {
    @Step("Страница «Кейсы» открыта")
    public void shouldBeOpened() {
        $x("//main | //section").shouldBe(visible);
    }
}

