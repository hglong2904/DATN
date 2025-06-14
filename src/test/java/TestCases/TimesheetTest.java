package TestCases;

import Common.BaseSetup;
import Common.UIHelper;
import Pages.TimesheetPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TimesheetTest extends BaseSetup  {
    TimesheetPage timesheetPage;
    UIHelper UIHelper;

    @BeforeClass
    public void initPage() {
        timesheetPage = new TimesheetPage(driver);
        UIHelper = new UIHelper(driver);
    }

    @BeforeMethod
    public void runRecruitmentTab() throws Exception {
        timesheetPage.clickTabTime();
        Thread.sleep(2000);
        timesheetPage.selectTimesheetOption("My Timesheets");
    }

    @Test(priority = 1, description = "Log Work thành công")
    public void logWorkHoursSuccessfully() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickDeleteBtn();
        Thread.sleep(2000);
        timesheetPage.enterProject1("NS_ODC");
        Thread.sleep(2000);
        timesheetPage.selectActivity("Coding");
        Thread.sleep(2000);
        timesheetPage.enterInputWorkHoursByIndex(0, "7");
        timesheetPage.enterInputWorkHoursByIndex(1, "8");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
    }

    @Test(priority = 2, description = "Log Work nhưng không nhập thời gian")
    public void logWorkHoursMissingTime() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickDeleteBtn();
        Thread.sleep(2000);
        timesheetPage.enterProject1("NS_ODC");
        Thread.sleep(2000);
        timesheetPage.selectActivity("Coding");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
    }

    @Test(priority = 3, description = "Log Work nhưng để trống trường Project")
    public void logWorkHoursMissingProject() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickDeleteBtn();
        Thread.sleep(2000);
        timesheetPage.enterInputWorkHoursByIndex(0, "7");
        timesheetPage.enterInputWorkHoursByIndex(1, "8");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Select a Project"), "Select a Project");
    }

    @Test(priority = 4, description = "log Work nhưng để trống trường Acitivity")
    public void logWorkHoursMissingActivity() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickDeleteBtn();
        Thread.sleep(2000);
        timesheetPage.enterProject1("NS_ODC");
        Thread.sleep(2000);
        timesheetPage.enterInputWorkHoursByIndex(0, "7");
        timesheetPage.enterInputWorkHoursByIndex(1, "8");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Select an Activity"), "Select an Activity");
    }

    @Test(priority = 5, description = "Log Work nhưng nhập sai định dạng giờ")
    public void invalidWorklogFormat() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickDeleteBtn();
        Thread.sleep(2000);
        timesheetPage.enterProject1("NS_ODC");
        Thread.sleep(2000);
        timesheetPage.selectActivity("Coding");
        Thread.sleep(2000);
        timesheetPage.enterInputWorkHoursByIndex(0, "abc");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Should Be Less Than 24 and in HH:MM or Decimal Format"), "Should Be Less Than 24 and in HH:MM or Decimal Format");
    }

    @Test(priority = 6, description = "Log Work với số giờ lớn hơn 24h")
    public void logWorkBeyond24Hours() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickDeleteBtn();
        Thread.sleep(2000);
        timesheetPage.enterProject1("NS_ODC");
        Thread.sleep(2000);
        timesheetPage.enterInputWorkHoursByIndex(0, "25");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Should Be Less Than 24 and in HH:MM or Decimal Format"), "Should Be Less Than 24 and in HH:MM or Decimal Format");
    }

    @Test(priority = 7, description = "Log work thêm cho dự án mới thành công")
    public void addNewRowSuccessfully() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickAddBtn();
        Thread.sleep(2000);
        timesheetPage.enterProject1("Nippon");
        Thread.sleep(2000);
        timesheetPage.enterInputWorkHoursByIndex(9, "7");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
        Thread.sleep(2000);
    }

    @Test(priority = 8, description = "Log Work với tổng số giờ trong 1 ngày lớn hơn 24h")
    public void totalWorkTimeExceeds24Hours() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickAddBtn();
        Thread.sleep(2000);
        timesheetPage.enterProject2("Nippon");
        Thread.sleep(2000);
        timesheetPage.enterInputWorkHoursByIndex(9, "20");
        Thread.sleep(2000);
        timesheetPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Total Should Be Less Than 24 Hours"), "Total Should Be Less Than 24 Hours");
    }

    @Test(priority = 9, description = "Reset Timesheet")
    public void resetLogWork() throws Exception {
        timesheetPage.clickEditBtn();
        Thread.sleep(2000);
        timesheetPage.clickResetBtn();
        Thread.sleep(2000);
    }
}
