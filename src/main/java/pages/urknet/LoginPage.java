package pages.urknet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import bean.User;
import pages.BasePage;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_URL = "https://mail.ukr.net/";

    private By loginField = By.name("login");
    private By passwordField = By.cssSelector("[name='password']");
    private By submitButton = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
        pageUrl = LOGIN_PAGE_URL;
    }

    public void navigate() {
        driver.get(pageUrl);
    }

    public void login(User user) {
        driver.findElement(loginField).sendKeys(user.getLogin());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }
}
