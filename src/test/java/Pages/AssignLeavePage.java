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

public class AssignLeavePage {

    private final WebDriver driver;
    private final UIHelper UIHelper;
    private final WebDriverWait wait;

    private final By tabAssignLeave = By.xpath("//li[@class='oxd-topbar-body-nav-tab']//a[text()='Assign Leave']");
    private final By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By dropdownLeaveType = By.xpath("//div[contains(text(),'-- Select --')]");
    private final By fromDateInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By toDateInput = By.xpath("(//div[@class='oxd-date-input'])[2]//input");
    private final By dropdownDuration = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");
    private final By btnAssign = By.xpath("//button[normalize-space()='Assign']");

    public AssignLeavePage(WebDriver driver){
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickAssignLeaveTab(){
        WebElement tabApply = wait.until(ExpectedConditions.elementToBeClickable(tabAssignLeave));
        tabApply.click();
    }

    public void enterEmployeeName(String employeeName) throws Exception{
        UIHelper.setText(employeeNameInput, employeeName);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void selectLeaveType(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownLeaveType)).click();
        By optionLeaveType = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionLeaveType));
        wait.until(ExpectedConditions.elementToBeClickable(optionLeaveType)).click();
    }

    public void enterFromDate(String fromDate) throws Exception {
        UIHelper.setText(fromDateInput, fromDate);
    }

    public void enterToDate(String toDate) throws Exception {
        UIHelper.setText(toDateInput, toDate);
    }

    public void selectDuration(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownDuration)).click();
        By optionDuration = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionDuration));
        wait.until(ExpectedConditions.elementToBeClickable(optionDuration)).click();
    }

    public void clickAssignBtn(){
        UIHelper.clickElement(btnAssign);
    }
}
