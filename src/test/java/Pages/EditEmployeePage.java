package Pages;

import Common.UIHelper;
import org.checkerframework.checker.guieffect.qual.UI;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;

public class EditEmployeePage {
    private final WebDriver driver;
    private final UIHelper UIHelper;
    private final WebDriverWait wait;

    private final By BtnEdit = By.xpath("//button[i[contains(@class,'bi-pencil-fill')]]");
    private final By FirstNameEdit = By.name("firstName");
    private final By MiddleNameEdit = By.name("middleName");
    private final By LastNameEdit  = By.name("lastName");
    private final By BtnSave = By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space");
    private final By DatePicker = By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
    private final By RadioBtnMale = By.xpath("//label[normalize-space()='Male']");
    private final By RadioBtnFemale = By.xpath("//label[normalize-space()='Female']");
    private final By BtnYes = By.cssSelector(".oxd-button--label-danger");
    private final By BtnNo = By.cssSelector("button[type='button'].oxd-button.oxd-button--ghost");

    public EditEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public void clickBtnEdit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btnEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(BtnEdit));
        WebElement scrollContainer = driver.findElement(By.className("oxd-table-body"));
        int yOffset = btnEdit.getLocation().getY() - scrollContainer.getLocation().getY();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[1];", scrollContainer, yOffset);
        wait.until(ExpectedConditions.elementToBeClickable(btnEdit)).click();
    }
    public void UpdateEmployeeName(String first, String middle, String last) throws Exception {
        clickBtnEdit();
        UIHelper.waitForFullPageLoad();
        wait.until(ExpectedConditions.elementToBeClickable(MiddleNameEdit));
        UIHelper.setText(MiddleNameEdit, middle);
        wait.until(ExpectedConditions.elementToBeClickable(FirstNameEdit));
        UIHelper.setText(FirstNameEdit, first);
        wait.until(ExpectedConditions.elementToBeClickable(LastNameEdit));
        UIHelper.setText(LastNameEdit, last);
        wait.until(ExpectedConditions.elementToBeClickable(BtnSave));
        UIHelper.clickElement(BtnSave);
    }
    public void enterDateOfBirth(String date) throws Exception {
        clickBtnEdit();
        UIHelper.waitForFullPageLoad();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dobInput = wait.until(ExpectedConditions.elementToBeClickable(DatePicker));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", dobInput);
        UIHelper.waitForFullPageLoad();
        UIHelper.clickElement(DatePicker);
        UIHelper.setText(DatePicker, date);
        UIHelper.clickElement(BtnSave);
    }
    public void updateGender(String gender){
        clickBtnEdit();
        UIHelper.waitForFullPageLoad();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-loading-spinner")));
        By radioLocator = gender.equalsIgnoreCase("Male") ? RadioBtnMale : RadioBtnFemale;
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(radioLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radioButton);
        UIHelper.waitForFullPageLoad();
        radioButton.click();
        UIHelper.clickElement(BtnSave);
    }
    public void clickBtnDelete(int index) {
        By deleteBtnLocator = By.xpath("(//button[contains(@class,'oxd-icon-button')]/i[contains(@class,'bi-trash')])[" + index + "]");
        try {
            UIHelper.waitForFullPageLoad();
            WebElement btnDelete = wait.until(ExpectedConditions.elementToBeClickable(deleteBtnLocator));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    btnDelete
            );
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDelete);
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Khong tim thay nut Delete thu " + index);
        }
    }
    public void clickBtnDelete_ConfirmYes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btnYes = wait.until(ExpectedConditions.elementToBeClickable(BtnYes));
        btnYes.click();
    }
    public void clickBtnDelete_ConfirmNo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btnNo = wait.until(ExpectedConditions.elementToBeClickable(BtnNo));
        btnNo.click();
    }
}

