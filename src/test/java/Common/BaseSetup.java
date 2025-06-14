package Common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BaseSetup {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static UIHelper UIHelper;

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginBtn = By.xpath("//button[@type='submit']");

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        UIHelper = new UIHelper(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("http://localhost/orangehrm");
        UIHelper.waitForPageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        UIHelper.setText(usernameInput, "admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        UIHelper.setText(passwordInput, "Admin@123456789");
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        UIHelper.clickElement(loginBtn);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

