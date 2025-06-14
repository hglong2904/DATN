package TestCases;

import Common.BaseSetup;
import Common.UIHelper;
import Pages.AllocateLeaveDaysPage;
import Pages.AssignLeavePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AssignLeaveTest extends BaseSetup {
    AssignLeavePage assignLeavePage;
    UIHelper UIHelper;
    AllocateLeaveDaysPage allocateLeaveDaysPage;

    @BeforeClass
    public void initPage(){
        assignLeavePage = new AssignLeavePage(driver);
        UIHelper = new UIHelper(driver);
        allocateLeaveDaysPage = new AllocateLeaveDaysPage(driver);
    }

    @BeforeMethod
    public void runLeaveTab() throws Exception {
        allocateLeaveDaysPage.clickTabLeave();
        Thread.sleep(2000);
        assignLeavePage.clickAssignLeaveTab();
        Thread.sleep(2000);
    }

    @Test(priority = 1, description = "Tạo đơn nghỉ phép cho 1 nhân viên thành công")
    public void AssignLeaveSuccessfully() throws Exception{
        assignLeavePage.enterEmployeeName("Long");
        Thread.sleep(2000);
        assignLeavePage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        assignLeavePage.enterFromDate("2025-05-24");
        Thread.sleep(2000);
        assignLeavePage.enterToDate("2025-05-24");
        Thread.sleep(2000);
        assignLeavePage.selectDuration("Full Day");
        Thread.sleep(2000);
        assignLeavePage.clickAssignBtn();
    }

    @Test(priority = 2, description = "Tạo đơn nghỉ phép cho nhân viên nhưng để trống tên nhân viên")
    public void AssignLeaveMissingEmployeeName() throws Exception{
        assignLeavePage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        assignLeavePage.enterFromDate("2025-05-24");
        Thread.sleep(2000);
        assignLeavePage.enterToDate("2025-05-24");
        Thread.sleep(2000);
        assignLeavePage.selectDuration("Full Day");
        Thread.sleep(2000);
        assignLeavePage.clickAssignBtn();
    }

    @Test(priority = 3, description = "Tạo đơn nghỉ phép cho nhân viên nhưng để trống loại nghỉ")
    public void AssignLeaveMissingLeaveType() throws Exception{
        assignLeavePage.enterFromDate("2025-05-24");
        Thread.sleep(2000);
        assignLeavePage.enterToDate("2025-05-24");
        Thread.sleep(2000);
        assignLeavePage.selectDuration("Full Day");
        Thread.sleep(2000);
        assignLeavePage.clickAssignBtn();
    }

    @Test(priority = 4, description = "Tạo đơn nghỉ phép cho nhân nhưng để trống ngày bắt đầu")
    public void AssignLeaveMissingFromDate() throws Exception{
        assignLeavePage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        assignLeavePage.enterToDate("2025-05-25");
        Thread.sleep(2000);
        assignLeavePage.clickAssignBtn();
    }

    @Test(priority = 5, description = "Tạo đơn nghỉ phép cho nhân viên khi ngày bắt đầu lớn hơn ngày kết thúc")
    public void AssignLeaveWithFromDateAfterToDate() throws Exception{
        assignLeavePage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        assignLeavePage.enterFromDate("2025-05-30");
        Thread.sleep(2000);
        assignLeavePage.enterToDate("2025-05-20");
        Thread.sleep(2000);
        assignLeavePage.clickAssignBtn();
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("To date should be after from date"), "To date should be after from date");
    }

    @Test(priority = 6, description = "Tạo đơn nghỉ phép cho nhân viên khi để trống tất cả các trường")
    public void AssignLeaveMissingAll() throws Exception{
        assignLeavePage.clickAssignBtn();
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }
}
