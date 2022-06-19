package pages.urknet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class HomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://mail.ukr.net/desktop";

    private By writeLetterButton = By.cssSelector(".primary.compose");
    private By toInput = By.name("toFieldInput");
    private By subjectInput = By.name("subject");
    private By letterBody = By.id("tinymce");
    private By sendButton = By.cssSelector(".screen__head .send.button");
    private By bodyIFrame = By.cssSelector("#mce_0_ifr");
    private By emailSentMessage = By.className("sendmsg__ads-ready");

    public HomePage(WebDriver driver) {
        super(driver);
        pageUrl = HOME_PAGE_URL;
    }

    public void clickWriteLetter() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(writeLetterButton));
        driver.findElement(writeLetterButton).click();
    }

    public void writeLetter(String to, String subject, String body) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(toInput));
        driver.findElement(toInput).sendKeys(to);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(subjectInput));
        driver.findElement(subjectInput).sendKeys(subject);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bodyIFrame));
        driver.switchTo().frame(driver.findElement(bodyIFrame));

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(letterBody));
        driver.findElement(letterBody).sendKeys(body);
        driver.switchTo().parentFrame();
    }

    public void sendLetter() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(sendButton));
        driver.findElement(sendButton).click();
    }

    public void checkEmailIsSent() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(emailSentMessage));
    }
}
