package pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected String pageUrl;
    protected WebDriverWait webDriverWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public BasePage() {
      PageFactory.initElements(WebDriverRunner.getWebDriver(), this);
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void waitUntilLoaded() {
        webDriverWait.until(ExpectedConditions.urlContains(pageUrl));
    }

    public void navigate() {
        driver.get(pageUrl);
    }

    public void waitUntilJsIsReady() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}