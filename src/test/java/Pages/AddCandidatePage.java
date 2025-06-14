package Pages;

import Common.UIHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCandidatePage {
    private final WebDriver driver;
    private final UIHelper UIHelper;
    private final WebDriverWait wait;

    private final By TabRecruitment = By.xpath("//span[text()='Recruitment']");
    private final By BtnAddCandidate = By.xpath("//button[normalize-space()='Add']");
    private final By FirstNameInput = By.name("firstName");
    private final By MiddleNameInput = By.name("middleName");
    private final By LastNameInput = By.name("lastName");
    private final By Email = By.xpath("//div[3]//div[1]//div[1]//div[1]//div[2]//input[1]");
    private final By BtnSave = By.cssSelector("button[type='submit']");
    private final By BtnCancel = By.xpath("//button[normalize-space()='Cancel']");
    private final By BtnDelete = By.xpath("(//i[@class='oxd-icon bi-trash'])[2]");
    private final By ConfirmYes = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By ConfirmNo = By.xpath("//button[normalize-space()='No, Cancel']");

    public AddCandidatePage(WebDriver driver) {
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickTabRecruitment(){
        WebElement tabRecruitment = wait.until(ExpectedConditions.elementToBeClickable(TabRecruitment));
        tabRecruitment.click();
    }
    public void clickBtnAdd(){
        UIHelper.clickElement(BtnAddCandidate);
    }
    public void fillCandidateForm(String first, String middle, String last, String email) throws Exception {

        wait.until(ExpectedConditions.elementToBeClickable(FirstNameInput));
        UIHelper.setText(FirstNameInput, first);

        wait.until(ExpectedConditions.elementToBeClickable(MiddleNameInput));
        UIHelper.setText(MiddleNameInput, middle);

        wait.until(ExpectedConditions.elementToBeClickable(LastNameInput));
        UIHelper.setText(LastNameInput, last);

        wait.until(ExpectedConditions.elementToBeClickable(Email));
        UIHelper.setText(Email, email);

        wait.until(ExpectedConditions.elementToBeClickable(BtnSave));
    }
    public void clickSaveBtn() {
        UIHelper.clickElement(BtnSave);
    }
    public void clickCancelBtn() {
        UIHelper.clickElement(BtnCancel);
    }
    public void clickBtnDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btnDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(BtnDelete));
        WebElement scrollContainer = driver.findElement(By.className("oxd-table-body"));
        int yOffset = btnDelete.getLocation().getY() - scrollContainer.getLocation().getY();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[1];", scrollContainer, yOffset);
        wait.until(ExpectedConditions.elementToBeClickable(btnDelete)).click();
    }
    public void confirmDelete() {
        UIHelper.clickElement(ConfirmYes);
    }
    public void cancelDelete() {
        UIHelper.clickElement(ConfirmNo);
    }
}

