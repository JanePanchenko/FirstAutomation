package pages.urknet;

import bean.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_URL = "https://mail.ukr.net/";

    @FindBy(name = "login")
    private WebElement loginField;
    @FindBy(css = "[name='password']")
    private WebElement passwordField;
    @FindBy(css = "[type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        pageUrl = LOGIN_PAGE_URL;
    }

    public void navigate() {
        driver.get(pageUrl);
    }

    public void login(User user) {
        loginField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        submitButton.click();
    }
}
