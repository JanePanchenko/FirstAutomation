package pages.testleaf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class WaitForDisappearancePage extends BasePage {

    public static final String BASE_URL = "http://www.leafground.com/pages/disapper.html";

    @FindBy(id = "btn")
    private WebElement button;

    public WaitForDisappearancePage(WebDriver driver) {
        super(driver);
        pageUrl = BASE_URL;
    }

    public void waitForDisappearance() {
        webDriverWait.until(ExpectedConditions.invisibilityOf(button));
    }
}
