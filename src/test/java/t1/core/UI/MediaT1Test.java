package t1.core.UI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import t1.core.BaseTest;
import t1.core.pages.media.MediaPage;
import t1.core.pages.media.NewsDetailsPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static t1.core.constans.UrlPages.MEDIA_URL;

@Tag("MED")
@DisplayName("Media. Медиацентр")
public class MediaT1Test extends BaseTest {

    MediaPage media = new MediaPage();
    NewsDetailsPage news = new NewsDetailsPage();

    @Test
    @Tag("MED-001")
    @DisplayName("MED-001. Список новостей отображается")
    void med001_newsListVisible() {

        step("SRZ: Открыть страницу Медиацентра", () -> {
            open(MEDIA_URL);
            media.shouldBeOpened();
        });

        step("SRZ: Список новостей отображается", () -> {
            media.newsListShouldBeVisible();
        });
    }

    @Test
    @Tag("MED-002")
    @DisplayName("MED-002. Открытие новости (дата и заголовок)")
    void med002_openNews() {

        step("SRZ: Открыть страницу Медиацентра", () -> {
            open(MEDIA_URL);
            media.shouldBeOpened();
        });

        step("SRZ: Открыть первую новость", () -> {
            media.openFirstNews();
        });

        step("SRZ: Новость открыта, дата и заголовок отображаются", () -> {
            news.shouldBeOpened();
            news.dateAndTitleShouldBeVisible();
        });
    }
}
