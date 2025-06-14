package Common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UIHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    private final int timeoutWaitForPageLoaded = 50;

    //Hàm khởi tạo - truyền WebDriver và khởi tạo WebDriverWait
    public UIHelper(WebDriver _driver) {
        driver = _driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //Chờ phần tử loader biến mất khỏi trang
    public void waitForLoaderToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-form-loader')]")));
    }
    //Chờ cho toàn bộ trang tải xong (bao gồm cả tài nguyên jQuery nếu có)
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = driver -> {
            boolean isDocumentReady = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            boolean isJQueryDone = true;
            try {
                isJQueryDone = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (window.jQuery == null) || (jQuery.active == 0)");
            } catch (Exception e) {
            }
            return isDocumentReady && isJQueryDone;
        };
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutWaitForPageLoaded))
                    .until(expectation);
        } catch (TimeoutException e) {
            throw new AssertionError("Timeout waiting for page to load completely");
        }
    }
    //Chờ trang tải đầy đủ và loader biến mất
    public void waitForFullPageLoad() {
        waitForPageLoaded();
        waitForLoaderToDisappear();
    }
    // Đặt giá trị cho một ô input (xóa giá trị cũ trước khi nhập mới)
    public void setText(By element, String value) throws Exception {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);
        Thread.sleep(1000);
        el.sendKeys(value);
    }
    //Click vào một phần tử sau khi đảm bảo nó có thể click được
    public void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }
    //Kiểm tra Page title
    public boolean verifyPageTitle(By element, String textValue) {
        return driver.findElement(element).getText().equals(textValue);
    }
    // Lấy thông báo thành công từ toast message
    public String getSuccessToastMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By toastLocator = By.cssSelector(".oxd-toast-content--success");
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
            return toast.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }
    // Lấy thông báo "No Records Found" từ toast message
    public String getNoRecordToastMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By toastLocator = By.cssSelector(".oxd-toast .oxd-toast-content");
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
            return toast.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }
    //Kiểm tra hiển thị thông báo lỗi
    public boolean isErrorMessageDisplayed(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By errorLocator = By.xpath("//span[text()='" + message + "']");
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
