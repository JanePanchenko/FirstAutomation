package pages.ukrnet;

import bean.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.mailinator.MailinatorEmailPage;
import pages.mailinator.MailinatorHomePage;
import pages.mailinator.MailinatorInboxPage;
import pages.urknet.HomePage;
import pages.urknet.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class UkrNetTest {

    private static final String MAILINATOR_EMAIL = "Yevheniia_Panchenko@mailinator.com";
    private static final String URK_NET_EMAIL = "Yevheniia_Panchenko@ukr.net";
    private static final String UKR_NET_PASSWORD = "*******";
    private static final String EMAIL_SUBJECT = "Email Subject";
    private static final String EMAIL_BODY = "Email Body";

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void shouldSendEmailToMailinator() {
        loginToMail(URK_NET_EMAIL, UKR_NET_PASSWORD);
        sendEmail(MAILINATOR_EMAIL, EMAIL_SUBJECT, EMAIL_BODY);

        loginToMailinator(MAILINATOR_EMAIL);
        openExpectedEmailBySubject(EMAIL_SUBJECT);
        verifyReceivedEmailData(URK_NET_EMAIL, EMAIL_BODY);
    }

    private void loginToMail(String email, String password) {
        User user = new User(email, password);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login(user);
    }

    private void sendEmail(String targetEmail, String subject, String body) {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilLoaded();
        homePage.waitUntilJsIsReady();
        homePage.clickWriteLetter();
        homePage.writeLetter(targetEmail, subject, body);
        homePage.sendLetter();
        homePage.checkEmailIsSent();
    }

    private void loginToMailinator(String email) {
        MailinatorHomePage mailinatorHomePage = new MailinatorHomePage(driver);
        mailinatorHomePage.navigate();
        mailinatorHomePage.waitUntilLoaded();
        mailinatorHomePage.loginToInbox(email);
    }

    private void openExpectedEmailBySubject(String subject) {
        MailinatorInboxPage mailinatorInboxPage = new MailinatorInboxPage(driver);
        mailinatorInboxPage.waitUntilJsIsReady();
        mailinatorInboxPage.openEmailBySubject(subject);
    }

    private void verifyReceivedEmailData(String expectedSender, String expectedBody) {
        MailinatorEmailPage emailPage = new MailinatorEmailPage(driver);
        emailPage.waitUntilJsIsReady();

        assertThat(emailPage.getSender()).isEqualToIgnoringCase(expectedSender);
        assertThat(emailPage.getEmailBody()).isEqualTo(expectedBody);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
