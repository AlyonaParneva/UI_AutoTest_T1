package t1.core.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.ar.ArPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static t1.core.constans.UrlPages.AR2024_URL;

@Tag("AR")
@DisplayName("AR. Годовой отчет")
public class ArT1Test extends BaseTest {

    ArPage ar = new ArPage();

    @Test
    @Tag("AR-001")
    @DisplayName("AR-001. Реквизиты и внешние ссылки")
    void ar001_requisitesAndLinks() {

        step("SRZ: Открыть страницу годового отчета", () -> {
            open(AR2024_URL);
            ar.shouldBeOpened();
        });

        step("SRZ: Реквизиты и внешние ссылки отображаются", () -> {
            ar.requisitesAndLinksShouldBeVisible();
        });
    }

    @Test
    @Tag("AR-002")
    @DisplayName("AR-002. Раздел «Продукты, решения и сервисы»")
    void ar002_productsSection() {

        step("SRZ: Открыть страницу годового отчета", () -> {
            open(AR2024_URL);
            ar.shouldBeOpened();
        });

        step("SRZ: Раздел «Продукты, решения и сервисы» отображается", () -> {
            ar.productsSectionShouldBeVisible();
        });
    }
}
