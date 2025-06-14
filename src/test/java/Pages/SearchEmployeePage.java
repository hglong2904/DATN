package Pages;

import Common.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchEmployeePage {
    private final WebDriver driver;
    private final UIHelper UIHelper;
    private final WebDriverWait wait;

    // Các ô nhập tìm kiếm khác nhau
    private final By employeeNameField = By.xpath("(//div[contains(@class, 'oxd-autocomplete-text-input')]//input)[1]");
    private final By employeeIDField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By jobTitleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[3]");
    private final By subUnitDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[4]");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");

    public SearchEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.UIHelper = new UIHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Tìm kiếm bằng tên nhân viên
    public void searchByEmployeeName(String employeeName) throws Exception {
        UIHelper.setText(employeeNameField, employeeName);
        Thread.sleep(1000);
        driver.findElement(searchButton).click();
    }

    // Tìm kiếm bằng ID nhân viên
    public void searchByEmployeeID(String employeeID) throws Exception {
        UIHelper.setText(employeeIDField, employeeID);
        Thread.sleep(1000);
        driver.findElement(searchButton).click();
    }

    // Tìm kiếm theo đơn vị
    public void selectSubUnit(String optionText){
        wait.until(ExpectedConditions.elementToBeClickable(subUnitDropdown)).click();
        By optionSubUnit = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionSubUnit));
        wait.until(ExpectedConditions.elementToBeClickable(optionSubUnit)).click();
        driver.findElement(searchButton).click();
    }

    // Tìm kiếm theo Job Title
    public void selectJobTitle(String optionText){
        wait.until(ExpectedConditions.elementToBeClickable(jobTitleDropdown)).click();
        By optionJobTitle = By.xpath("//div[@role='listbox']//span[normalize-space()='" + optionText + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionJobTitle));
        wait.until(ExpectedConditions.elementToBeClickable(optionJobTitle)).click();
        driver.findElement(searchButton).click();
    }
}
