package pages.testleaf;

import bean.ProgrammingLanguage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class TestLeafCheckboxesPage extends BasePage {

    private static final String CHECKBOXES_PAGE_URL = "http://www.leafground.com/pages/checkbox.html";
    private static final String PROGRAMMING_LANGUAGE_XPATH_EXPRESSION =
            "//div[label[normalize-space()='Select the languages that you know?']]//input[%s]";
    private static final String SELENIUM_XPATH_EXPRESSION =
            "//div[label[normalize-space()='Confirm Selenium is checked']]//input[1]";
    private static final String I_AM_SELECTED_XPATH_EXPRESSION =
            "//div[label[normalize-space()='DeSelect only checked']]//input[2]";
    private static final String ALL_CHECKBOX_OPTIONS_XPATH_EXPRESSION =
            "//div[label[normalize-space()='Select all below checkboxes']]//input";

    @FindBy(xpath = SELENIUM_XPATH_EXPRESSION)
    private WebElement seleniumCheckbox;
    @FindBy(xpath = I_AM_SELECTED_XPATH_EXPRESSION)
    private WebElement iAmSelectedCheckbox;
    @FindBy(xpath = ALL_CHECKBOX_OPTIONS_XPATH_EXPRESSION)
    private List<WebElement> checkboxOptions;


    public TestLeafCheckboxesPage(WebDriver driver) {
        super(driver);
        pageUrl = CHECKBOXES_PAGE_URL;
    }

    public void checkProgrammingLanguage(ProgrammingLanguage language) {
        WebElement languageCheckbox = findProgrammingLanguageCheckbox(language);
        languageCheckbox.click();
    }

    public boolean isProgrammingLanguageChecked(ProgrammingLanguage language) {
        WebElement languageCheckbox = findProgrammingLanguageCheckbox(language);
        return languageCheckbox.isSelected();
    }

    private WebElement findProgrammingLanguageCheckbox(ProgrammingLanguage language) {
        String programmingLanguageXPath = String.format(PROGRAMMING_LANGUAGE_XPATH_EXPRESSION,
                language.getOrder());
        By programmingLanguageSelector = By.xpath(programmingLanguageXPath);
        return driver.findElement(programmingLanguageSelector);
    }

    public boolean isSeleniumCheckboxChecked() {
        return seleniumCheckbox.isSelected();
    }

    public void clickIAmSelectedCheckbox() {
        iAmSelectedCheckbox.click();
    }

    public boolean isIAmSelectedCheckboxUnchecked() {
        return !iAmSelectedCheckbox.isSelected();
    }

    public void checkAllCheckboxOptions() {
        checkboxOptions.forEach(WebElement::click);
    }

    public boolean areAllOptionCheckboxesSelected() {
        return checkboxOptions.stream()
                .allMatch(WebElement::isSelected);
    }
}
