package pages.testleaf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class WaitForAppearancePage extends BasePage {

    public static final String BASE_URL = "http://www.leafground.com/pages/appear.html";

    @FindBy(id = "btn")
    private WebElement textButton;

    public WaitForAppearancePage(WebDriver driver) {
        super(driver);
        pageUrl = BASE_URL;
    }

    public void waitForAppearance() {
        webDriverWait.until(ExpectedConditions.visibilityOf(textButton));
    }
}
