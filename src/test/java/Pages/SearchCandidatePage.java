package Pages;

import Common.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchCandidatePage {
    private final WebDriver driver;
    private final UIHelper UIHelper;
    private final WebDriverWait wait;

    // Locators
    private final By candidateNameInput = By.xpath("//label[text()='Candidate Name']/following::input[1]");
    private final By keywordsInput = By.xpath("//input[@placeholder='Enter comma seperated words...']");
    private final By jobTitleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private final By fromDateInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private final By toDateInput = By.xpath("//input[@placeholder='To']");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private final By candidateNameError = By.xpath("//label[text()='Candidate Name']/following::span[text()='Invalid']");

    // Constructor
    public SearchCandidatePage(WebDriver driver) {
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterCandidateName(String name) throws Exception {
        UIHelper.setText(candidateNameInput, name);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void enterKeywords(String keywords) {
        try {
            UIHelper.setText(keywordsInput, keywords);
        } catch (Exception e) {
            throw new RuntimeException("Không thể nhập Keywords", e);
        }
    }

    public void selectJobTitle(String optionText){
        wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropdown)).click();
        By optionJobTitle = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionJobTitle));
        wait.until(ExpectedConditions.elementToBeClickable(optionJobTitle)).click();
        driver.findElement(searchButton).click();
    }

    public void enterFromDate(String fromDate) throws Exception {
        UIHelper.setText(fromDateInput, fromDate);
    }

    public void enterToDate(String toDate) throws Exception {
        UIHelper.setText(toDateInput, toDate);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickReset() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    public boolean isNameInvalid() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(candidateNameError));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
