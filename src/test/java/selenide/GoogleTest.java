package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.google.GoogleHomePage;
import pages.selenide.SelenidePage;

public class GoogleTest {

    @BeforeClass
    public void setUp() {
        Configuration.holdBrowserOpen = false;
        Configuration.savePageSource = false;
        Configuration.timeout = 4000;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void userShouldBeAbleToSearch() {
        Selenide.open("https://google.com");

        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.waitUntilSearchFieldDisplayed()
                .setSearchText("Selenide")
                .pressEnter()
                .verifySearchResultsMoreThen(6)
                .clickOnTheFirstLink();

        SelenidePage selenidePage = new SelenidePage();
        selenidePage.verifySupportUkraineLabelIsPresent()
                .clickOnBlogButton()
                .checkPageTitle("Selenide blog");
    }
}
