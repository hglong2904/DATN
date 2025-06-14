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


public class AllocateLeaveDaysPage {
    private WebDriver driver;
    private UIHelper UIHelper;
    private WebDriverWait wait;

    private By TabLeave = By.xpath("//span[text()='Leave']");
    private By DropdownEntitlements = By.xpath("//span[normalize-space()='Entitlements']");
    private By RadioBtnMultiEmployee = By.xpath("//label[normalize-space()='Multiple Employees']");
    private By EmployeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private By DropdownLeaveTypeIndividual = By.xpath("//div[contains(text(),'-- Select --')]");
    private By DropdownLeaveTypeMultiple = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/div/div/div[1]");
    private By EntitlementsInput = By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
    private By DropdownSubUnit = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div/div[1]");
    private By BtnSave = By.cssSelector("button[type='submit']");
    private By BtnCancel = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--ghost']");
    private By BtnConfirm = By.xpath("//button[normalize-space()='Confirm']");

    public AllocateLeaveDaysPage(WebDriver driver){
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickTabLeave(){
        WebElement tabLeave = wait.until(ExpectedConditions.elementToBeClickable(TabLeave));
        tabLeave.click();
    }

    public void selectEntitlementsOption(String optionText) {
        // Click vào dropdown Entitlements
        wait.until(ExpectedConditions.elementToBeClickable(DropdownEntitlements)).click();
        // XPath để tìm đúng <a> có nội dung như optionText
        By optionLocator = By.xpath("//ul[@class='oxd-dropdown-menu']//a[normalize-space()='" + optionText + "']");
        // Chờ và click vào item có text khớp
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public void enterEmployeeName(String employeeName) throws Exception{
        UIHelper.setText(EmployeeNameInput, employeeName);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void enterEntitlements(String entitlements) throws Exception{
        UIHelper.setText(EntitlementsInput, entitlements);
    }

    public void selectLeaveTypeIndividual(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownLeaveTypeIndividual)).click();
        // XPath đến item có text tương ứng (ví dụ: "Du lịch", "Nghỉ chế độ thai sản", ...)
        By optionLeaveTypeIndividual = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        // Chờ mục xuất hiện và click
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionLeaveTypeIndividual));
        wait.until(ExpectedConditions.elementToBeClickable(optionLeaveTypeIndividual)).click();
    }

    public void selectLeaveTypeMultiple(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownLeaveTypeMultiple)).click();
        // XPath đến item có text tương ứng (ví dụ: "Du lịch", "Nghỉ chế độ thai sản", ...)
        By optionLeaveTypeMultiple = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        // Chờ mục xuất hiện và click
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionLeaveTypeMultiple));
        wait.until(ExpectedConditions.elementToBeClickable(optionLeaveTypeMultiple)).click();
    }

    public void selectOptMultiEmployee(){
        UIHelper.clickElement(RadioBtnMultiEmployee);
    }

    public void selectSubUnit(String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(DropdownSubUnit)).click();
        // XPath đến item có text tương ứng (ví dụ: "Du lịch", "Nghỉ chế độ thai sản", ...)
        By optionSubUnit = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        // Chờ mục xuất hiện và click
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionSubUnit));
        wait.until(ExpectedConditions.elementToBeClickable(optionSubUnit)).click();
    }

    public void clickSaveBtn() {
        UIHelper.clickElement(BtnSave);
    }

    public void clickCancelBtn() {
        UIHelper.clickElement(BtnCancel);
    }

    public void clickConfirmBtn(){
        UIHelper.clickElement(BtnConfirm);
    }
}
