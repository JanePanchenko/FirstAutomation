package pages.urknet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class HomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://mail.ukr.net/desktop";

    @FindBy(css = ".primary.compose")
    private WebElement writeLetterButton;
    @FindBy(name = "toFieldInput")
    private WebElement toInput;
    @FindBy(name = "subject")
    private WebElement subjectInput;
    @FindBy(id = "tinymce")
    private WebElement letterBody;
    @FindBy(css = ".screen__head .send.button")
    private WebElement sendButton;
    @FindBy(css = "#mce_0_ifr")
    private WebElement bodyIFrame;
    @FindBy(className = "sendmsg__ads-ready")
    private WebElement emailSentMessage;

    public HomePage(WebDriver driver) {
        super(driver);
        pageUrl = HOME_PAGE_URL;
    }

    public void clickWriteLetter() {
        webDriverWait.until(ExpectedConditions.visibilityOf(writeLetterButton));
        writeLetterButton.click();
    }

    public void writeLetter(String to, String subject, String body) {
        webDriverWait.until(ExpectedConditions.visibilityOf(toInput));
        toInput.sendKeys(to);

        webDriverWait.until(ExpectedConditions.visibilityOf(subjectInput));
        subjectInput.sendKeys(subject);

        webDriverWait.until(ExpectedConditions.visibilityOf(bodyIFrame));
        driver.switchTo().frame(bodyIFrame);

        webDriverWait.until(ExpectedConditions.visibilityOf(letterBody));
        letterBody.sendKeys(body);
        driver.switchTo().parentFrame();
    }

    public void sendLetter() {
        webDriverWait.until(ExpectedConditions.visibilityOf(sendButton));
        sendButton.click();
    }

    public void checkEmailIsSent() {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailSentMessage));
    }
}
