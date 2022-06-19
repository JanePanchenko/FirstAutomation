package pages.mailinator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class MailinatorHomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.mailinator.com/v4/public/inboxes.jsp";

    private By searchEmailInputField = By.className("primary-input");
    private By searchButton = By.className("primary-btn");

    public MailinatorHomePage(WebDriver driver) {
        super(driver);
        pageUrl = HOME_PAGE_URL;
    }

    public void loginToInbox(String email) {
        driver.findElement(searchEmailInputField).sendKeys(email);
        driver.findElement(searchButton).click();
    }
}
