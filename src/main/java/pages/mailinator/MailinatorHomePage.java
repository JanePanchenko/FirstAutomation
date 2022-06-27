package pages.mailinator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class MailinatorHomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.mailinator.com/v4/public/inboxes.jsp";

    @FindBy(className = "primary-input")
    private WebElement searchEmailInputField;
    @FindBy(className = "primary-btn")
    private WebElement searchButton;

    public MailinatorHomePage(WebDriver driver) {
        super(driver);
        pageUrl = HOME_PAGE_URL;
    }

    public void loginToInbox(String email) {
        searchEmailInputField.sendKeys(email);
        searchButton.click();
    }
}
