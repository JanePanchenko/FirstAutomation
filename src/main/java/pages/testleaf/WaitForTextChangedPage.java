package pages.testleaf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class WaitForTextChangedPage extends BasePage {

    public static final String BASE_URL = "http://www.leafground.com/pages/TextChange.html";

    @FindBy(id = "btn")
    private WebElement changeButton;

    public WaitForTextChangedPage(WebDriver driver) {
        super(driver);
        pageUrl = BASE_URL;
    }

    public void waitForTextInElement(String expectedText) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(changeButton, expectedText));
    }
}
