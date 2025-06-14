package Pages;

import Common.UIHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TimesheetPage {
    private WebDriver driver;
    private UIHelper UIHelper;
    private WebDriverWait wait;

    private By TabTime = By.xpath("//span[text()='Time']");
    private By DropdownTimesheets = By.xpath("//span[normalize-space()='Timesheets']");
    private By BtnEdit = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost' and @type='button']");
    private By ProjectInput1 = By.xpath("(//div[contains(@class, 'oxd-autocomplete-text-input')]//input)[1]");
    private By ProjectInput2 = By.xpath("(//div[contains(@class, 'oxd-autocomplete-text-input')]//input)[2]");
    private By DropdownActivity = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']");
    private By BtnSave = By.xpath("//button[contains(@class, 'oxd-button--secondary') and @type='submit']");
    private By BtnAdd = By.xpath("(//button[@class='oxd-icon-button orangehrm-timesheet-icon'])[2]");
    private By BtnDelete = By.xpath("(//button[@class='oxd-icon-button orangehrm-timesheet-icon'])[1]");
    private By WorkHoursInput = By.xpath("//input[@class='oxd-input oxd-input--active' and @autocomplete='off']");
    private By BtnReset = By.xpath("//button[@type='button' and contains(@class, 'oxd-button--ghost') and normalize-space()='Reset']");

    public TimesheetPage(WebDriver driver) {
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickTabTime() throws Exception {
        WebElement tabTime = wait.until(ExpectedConditions.elementToBeClickable(TabTime));
        tabTime.click();
    }

    public void selectTimesheetOption(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownTimesheets)).click();
        By optionTimesheet = By.xpath("//ul[@class='oxd-dropdown-menu']//a[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionTimesheet)).click();
    }

    public void enterProject1(String project) throws Exception {
        UIHelper.setText(ProjectInput1, project);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void enterProject2(String project) throws Exception {
        UIHelper.setText(ProjectInput2, project);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void selectActivity(String optionText){
        wait.until(ExpectedConditions.elementToBeClickable(DropdownActivity)).click();
        By optionActivity = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionActivity));
        wait.until(ExpectedConditions.elementToBeClickable(optionActivity)).click();
    }

    public void enterInputWorkHoursByIndex(int index, String value) {
        List<WebElement> workHours = driver.findElements(WorkHoursInput);
        if (index >= 0 && index < workHours.size()) {
            workHours.get(index).sendKeys(value);
        } else {
            throw new IllegalArgumentException("Index vượt quá số lượng input!");
        }
    }

    public void clickEditBtn(){
        UIHelper.clickElement(BtnEdit);
    }

    public void clickSaveBtn(){
        UIHelper.clickElement(BtnSave);
    }

    public void clickAddBtn(){
        UIHelper.clickElement(BtnAdd);
    }

    public void clickDeleteBtn(){
        UIHelper.clickElement(BtnDelete);
    }

    public void clickResetBtn(){
        UIHelper.clickElement(BtnReset);
    }
}
