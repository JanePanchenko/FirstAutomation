package pages.mailinator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class MailinatorInboxPage extends BasePage {

    private static final String EMAIL_BY_SUBJECT_XPATH_PATTERN = "//td[contains(text(), '%s')]";

    public MailinatorInboxPage(WebDriver driver) {
        super(driver);
    }

    public void openEmailBySubject(String subject) {
        String emailByPatternXPath = String.format(EMAIL_BY_SUBJECT_XPATH_PATTERN, subject);
        By receivedEmailXPath = By.xpath(emailByPatternXPath);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(receivedEmailXPath));
        driver.findElement(receivedEmailXPath).click();
    }
}
