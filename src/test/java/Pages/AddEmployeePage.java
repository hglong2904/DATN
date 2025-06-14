package Pages;

import Common.UIHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {
    private final WebDriver driver;
    private final UIHelper UIHelper;
    private final WebDriverWait wait;

    private final By TabPIM = By.xpath("//span[text()='PIM']");
    private final By BtnAddEmployee = By.xpath("//a[text()='Add Employee']");
    private final By FirstNameInput = By.name("firstName");
    private final By MiddleNameInput = By.name("middleName");
    private final By LastNameInput = By.name("lastName");
    private final By Id = By.xpath("//label[text()='Employee Id']/following::input[1]");
    private final By BtnSave = By.xpath("//button[@type='submit']");
    private final By BtnCancel = By.xpath("//button[normalize-space()='Cancel']");

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickTabPIM(){
        WebElement pimTab = wait.until(ExpectedConditions.elementToBeClickable(TabPIM));
        pimTab.click();
    }

    public void clickBtnAdd(){
        UIHelper.clickElement(BtnAddEmployee);
    }

    public void fillEmployeeForm(String first, String middle, String last) throws Exception{
        UIHelper.setText(FirstNameInput, first);
        wait.until(ExpectedConditions.elementToBeClickable(MiddleNameInput));

        UIHelper.setText(MiddleNameInput, middle);
        wait.until(ExpectedConditions.elementToBeClickable(LastNameInput));

        UIHelper.setText(LastNameInput, last);
        wait.until(ExpectedConditions.elementToBeClickable(BtnSave));
    }

    public void SetEmployeeID(String newID) throws Exception {
        UIHelper.setText(Id, newID);
    }

    public void clickSaveBtn() {
        UIHelper.clickElement(BtnSave);
    }

    public void clickCancelBtn() {
        UIHelper.clickElement(BtnCancel);
    }
}

