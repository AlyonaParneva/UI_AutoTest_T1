package t1.core.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.cases.CaseDetailsPage;
import t1.core.pages.cases.CasesPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("CASE")
@DisplayName("Cases. Каталог кейсов")
public class CasesT1Test extends BaseTest {

    CasesPage cases = new CasesPage();
    CaseDetailsPage caseCard = new CaseDetailsPage();

    @Test
    @Tag("CASE-001")
    @DisplayName("CASE-001. Каталог кейсов отображается")
    void case001_casesCatalogVisible() {

        step("SRZ: Открыть страницу кейсов", () -> {
            open("/cases");
            cases.shouldBeOpened();
        });

        step("SRZ: Отображаются карточки кейсов", () -> {
            cases.casesShouldBeVisible();
        });
    }

    @Test
    @Tag("CASE-002")
    @DisplayName("CASE-002. Фильтрация кейсов по отрасли")
    void case002_industryFiltering() {

        step("SRZ: Открыть страницу кейсов", () -> {
            open("/cases");
            cases.shouldBeOpened();
        });

        step("SRZ: Применить фильтр по отрасли", () -> {
            cases.selectFirstIndustryFilter();
        });

        step("SRZ: Отображаются отфильтрованные кейсы", () -> {
            cases.casesShouldBeVisible();
        });
    }

    @Test
    @Tag("CASE-003")
    @DisplayName("CASE-003. Открытие карточки кейса")
    void case003_openCaseCard() {

        step("SRZ: Открыть страницу кейсов", () -> {
            open("/cases");
            cases.shouldBeOpened();
        });

        step("SRZ: Открыть первую карточку кейса", () -> {
            cases.openFirstCaseCard();
        });

        step("SRZ: Карточка кейса открыта", () -> {
            caseCard.shouldBeOpened();
        });
    }

    @Test
    @Tag("CASE-004")
    @DisplayName("CASE-004. Структура контента кейса")
    void case004_caseContentStructure() {

        step("SRZ: Открыть страницу кейсов", () -> {
            open("/cases");
            cases.shouldBeOpened();
        });

        step("SRZ: Открыть первую карточку кейса", () -> {
            cases.openFirstCaseCard();
            caseCard.shouldBeOpened();
        });

        step("SRZ: Структура контента кейса отображается", () -> {
            caseCard.contentShouldBeVisible();
        });
    }

    @Test
    @Tag("CASE-005")
    @DisplayName("CASE-005. PDF-кейс доступен")
    void case005_pdfCaseAvailable() {

        step("SRZ: Открыть страницу кейсов", () -> {
            open("/cases");
            cases.shouldBeOpened();
        });

        step("SRZ: Открыть первую карточку кейса", () -> {
            cases.openFirstCaseCard();
            caseCard.shouldBeOpened();
        });

        step("SRZ: PDF-кейс доступен", () -> {
            caseCard.pdfShouldBeAvailable();
        });
    }
}
