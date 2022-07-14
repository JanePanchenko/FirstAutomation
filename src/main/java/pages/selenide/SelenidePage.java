package pages.selenide;

import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SelenidePage extends BasePage {

    private static final By SUPPORT_UKRAINE_LABEL = By.cssSelector(".donate_header h1");
    private static final By BLOG_BUTTON = By.xpath("//a[text() = 'Blog']");

    private static final String SUPPORT_UKRAINE_MESSAGE = "Selenide Supports Ukraine \uD83C\uDDFA\uD83C\uDDE6";
    private static final String TITLE_CSS_SELECTOR = "title";
    private static final String TEXT_ATTRIBUTE = "text";

    public SelenidePage verifySupportUkraineLabelIsPresent() {
        $(SUPPORT_UKRAINE_LABEL).shouldHave(text(SUPPORT_UKRAINE_MESSAGE));
        return this;
    }

    public SelenidePage clickOnBlogButton() {
        $(BLOG_BUTTON).click();
        return this;
    }

    public SelenidePage checkPageTitle(String expectedTitle) {
        $(TITLE_CSS_SELECTOR).shouldHave(attribute(TEXT_ATTRIBUTE, expectedTitle));
        return this;
    }
}
