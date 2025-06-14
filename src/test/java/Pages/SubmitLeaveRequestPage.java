package Pages;

import Common.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubmitLeaveRequestPage {

    private WebDriver driver;
    private UIHelper UIHelper;
    private WebDriverWait wait;

    private By TabApply = By.xpath("//li[@class='oxd-topbar-body-nav-tab']//a[text()='Apply']");
    private By DropdownLeaveType = By.xpath("//div[contains(text(),'-- Select --')]");
    private By FromDateInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By ToDateInput = By.xpath("(//div[@class='oxd-date-input'])[2]//input");
    private By DropdownDuration = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");
    private By DropdownPartialday = By.xpath("(//div[@class='oxd-select-text-input' and text()='-- Select --'])[2]");
    private By DropdownPartital_Duration = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[3]");
    private By BtnApply = By.xpath("//button[normalize-space()='Apply']");

    public SubmitLeaveRequestPage(WebDriver driver){
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickApplyTab(){
        WebElement tabApply = wait.until(ExpectedConditions.elementToBeClickable(TabApply));
        tabApply.click();
    }

    public void selectLeaveType(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownLeaveType)).click();
        By optionLeaveType = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionLeaveType));
        wait.until(ExpectedConditions.elementToBeClickable(optionLeaveType)).click();
    }

    public void enterFromDate(String fromDate) throws Exception {
        UIHelper.setText(FromDateInput, fromDate);
    }

    public void enterToDate(String toDate) throws Exception {
        UIHelper.setText(ToDateInput, toDate);
    }

    public void selectDuration(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownDuration)).click();
        By optionDuration = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionDuration));
        wait.until(ExpectedConditions.elementToBeClickable(optionDuration)).click();
    }

    public void selectPartialDay(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownDuration)).click();
        By optionPartial = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionPartial));
        wait.until(ExpectedConditions.elementToBeClickable(optionPartial)).click();
    }

    public void selectPartialDay_Duration(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownPartital_Duration)).click();
        By optionPartialDay_Duration = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionPartialDay_Duration));
        wait.until(ExpectedConditions.elementToBeClickable(optionPartialDay_Duration)).click();
    }

    public void clickApplyBtn(){
        UIHelper.clickElement(BtnApply);
    }
}
