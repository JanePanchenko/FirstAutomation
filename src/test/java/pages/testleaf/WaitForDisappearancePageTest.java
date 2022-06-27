package pages.testleaf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WaitForDisappearancePageTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void shouldWaitForDisappearanceOfElement() {
        WaitForDisappearancePage page = new WaitForDisappearancePage(driver);

        page.navigate();
        page.waitUntilLoaded();
        page.waitForDisappearance();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
