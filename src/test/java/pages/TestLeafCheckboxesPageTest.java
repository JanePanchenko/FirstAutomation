package pages;

import bean.ProgrammingLanguage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestLeafCheckboxesPageTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void shouldTickJavaCheckbox() {
        TestLeafCheckboxesPage page = new TestLeafCheckboxesPage(driver);

        page.navigate();
        page.waitUntilLoaded();
        page.checkProgrammingLanguage(ProgrammingLanguage.JAVA);

        assertThat(page.isProgrammingLanguageChecked(ProgrammingLanguage.JAVA)).isTrue();
    }

    @Test
    public void shouldHaveSeleniumCheckboxSelectedByDefault() {
        TestLeafCheckboxesPage page = new TestLeafCheckboxesPage(driver);

        page.navigate();
        page.waitUntilLoaded();

        assertThat(page.isSeleniumCheckboxChecked()).isTrue();
    }

    @Test
    public void shouldUncheckIAmSelectedCheckbox() {
        TestLeafCheckboxesPage page = new TestLeafCheckboxesPage(driver);

        page.navigate();
        page.waitUntilLoaded();
        page.clickIAmSelectedCheckbox();

        assertThat(page.isIAmSelectedCheckboxUnchecked()).isTrue();
    }

    @Test
    public void shouldCheckAllCheckboxOptionsFromLastSection() {
        TestLeafCheckboxesPage page = new TestLeafCheckboxesPage(driver);

        page.navigate();
        page.waitUntilLoaded();
        page.checkAllCheckboxOptions();

        assertThat(page.areAllOptionCheckboxesSelected()).isTrue();
    }
}
