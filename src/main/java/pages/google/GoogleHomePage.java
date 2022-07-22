package pages.google;

import com.codeborne.selenide.CollectionCondition;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleHomePage extends BasePage {

    private static final By SEARCH_FIELD = By.cssSelector("[name = 'q']");
    private static final By SEARCH_RESULT = By.xpath("//h3[contains(text(), 'Selenide')]");

    public GoogleHomePage waitUntilSearchFieldDisplayed() {
         $(SEARCH_FIELD).shouldBe(visible);
         return this;
    }

    public GoogleHomePage setSearchText(String text) {
        $(SEARCH_FIELD).setValue(text);
        return this;
    }

    public GoogleHomePage pressEnter() {
        $(SEARCH_FIELD).pressEnter();
        return this;
    }

    public GoogleHomePage verifySearchResultsMoreThen(int graterThenElements) {
        $$(SEARCH_RESULT)
                .filter(visible)
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(graterThenElements));
        return this;
    }

    public GoogleHomePage clickOnTheFirstLink() {
        $$(SEARCH_RESULT).get(0).click();
        return this;
    }
}
