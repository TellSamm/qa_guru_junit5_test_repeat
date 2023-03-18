package ru.sammtell;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка работы сайта YAHOO.com")
public class HomeWorkCommentsTest {

    @BeforeEach
    void openSite() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        open("https://www.yahoo.com/");
    }

    @Disabled
    @ValueSource(strings = {
            "JAVA", "Python"
    })
    @ParameterizedTest(name = "Проверка числа результатов поиска по тексту на сайте YAHOO.com {0}")
    @Tag("HIGH")
    void searchResultsYahoo(String testData) {
        $("#ybar-search-box-container").click();
        $("input[type='text']:nth-of-type(1)").setValue(testData);
        $("#ybar-search").click();
        $$("div.dd.algo").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(5));
    }


    @CsvSource({
            "JAVA,   Java | Oracle",
            "Python,  Welcome to Python.org"
    })
    @ParameterizedTest(name = "В результате поиска  по тексту {0} на сайте YAHOO.com должен отображаться текст {1}")
    @Tag("HIGH")
    void searchResultsYahoo2(String testData, String expectedText) {
        $("#ybar-search-box-container").click();
        $("input[type='text']:nth-of-type(1)").setValue(testData);
        $("#ybar-search").click();
        $("div.dd.algo").shouldHave(text(expectedText));
    }

    //@Disabled
    @CsvFileSource(resources = "/testdata/searchResultsYahoo2.csv")
    @ParameterizedTest(name = "В результате поиска  по тексту {0} на сайте YAHOO.com должен отображаться текст {1}")
    @Tag("HIGH")
    void searchResultsYahoo3(String testData, String expectedText) {
        $("#ybar-search-box-container").click();
        $("input[type='text']:nth-of-type(1)").setValue(testData);
        $("#ybar-search").click();
        $("div.dd.algo").shouldHave(text(expectedText));
    }


    @Disabled
    @Test
    @DisplayName("Проверка выдачи резульатов кол-ва мобильных приложений при клике на кнопку Mobile Apps в разделе MORE")
    @Tag("HIGH")
    void SubjectMobileApps() {
        $("#root_9").click();
        $("div#YDC-Col1 a[href^='https://mobile']").click();
        $$(".app-inner").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(7));

    }


}
