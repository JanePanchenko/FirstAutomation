package pages.mailinator;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class MailinatorEmailPage extends BasePage {

    @FindBy(xpath = "(//div[contains(concat(' ', normalize-space(@class), ' '), ' from ')])[2]")
    private WebElement senderElement;
    @FindBy(id = "html_msg_body")
    private WebElement emailBodyIFrame;
    @FindBy(className = "xfmc1")
    private WebElement emailBodyElement;

    public MailinatorEmailPage(WebDriver driver) {
        super(driver);
    }

    public String getSender() {
        webDriverWait.until(driver -> StringUtils.isNotEmpty(senderElement.getText()));
        return senderElement.getText();
    }

    public String getEmailBody() {
        driver.switchTo().frame(emailBodyIFrame);
        webDriverWait.until(driver -> StringUtils.isNotEmpty(emailBodyElement.getText()));
        return emailBodyElement.getText();
    }
}
