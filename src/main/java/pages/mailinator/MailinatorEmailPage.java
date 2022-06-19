package pages.mailinator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.regex.Pattern;

public class MailinatorEmailPage extends BasePage {

    private static final Pattern NON_EMPTY_VALUE_PATTERN = Pattern.compile("^(?=\\s*\\S).*$");

    private By senderSelector = By.xpath("(//div[contains(concat(' ', normalize-space(@class), ' '), ' from ')])[2]");
    private By emailBodyIFrame = By.id("html_msg_body");
    private By emailBodySelector = By.className("xfmc1");

    public MailinatorEmailPage(WebDriver driver) {
        super(driver);
    }

    public String getSender() {
        webDriverWait.until(ExpectedConditions.textMatches(senderSelector, NON_EMPTY_VALUE_PATTERN));
        WebElement emailWebElement = driver.findElement(senderSelector);
        return emailWebElement.getText();
    }

    public String getEmailBody() {
        driver.switchTo().frame(driver.findElement(emailBodyIFrame));
        webDriverWait.until(ExpectedConditions.textMatches(emailBodySelector, NON_EMPTY_VALUE_PATTERN));
        WebElement emailBodyWebElement = driver.findElement(emailBodySelector);
        return emailBodyWebElement.getText();
    }
}
