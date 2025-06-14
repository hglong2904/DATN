package TestCases;

import Common.BaseSetup;
import Common.UIHelper;
import Pages.AllocateLeaveDaysPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AllocateLeaveDaysTest extends BaseSetup {
    AllocateLeaveDaysPage allocateLeaveDaysPage;
    UIHelper UIHelper;

    @BeforeClass
    public void initPage(){
        allocateLeaveDaysPage = new AllocateLeaveDaysPage(driver);
        UIHelper = new UIHelper(driver);
    }

    @BeforeMethod
    public void runLeaveTab() throws Exception {
        allocateLeaveDaysPage.clickTabLeave();
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectEntitlementsOption("Add Entitlements");
    }

    @Test(priority = 1, description = "Phân bổ ngày nghỉ phép cho 1 nhân viên thành công")
    public void allocateLeaveSuccessfully() throws Exception {
        allocateLeaveDaysPage.enterEmployeeName("Triệu");
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectLeaveTypeIndividual("Nghỉ ốm");
        Thread.sleep(2000);
        allocateLeaveDaysPage.enterEntitlements("10");
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickSaveBtn();
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickConfirmBtn();
    }

    @Test(priority = 2, description = "Phân bổ ngày nghỉ khi để trống tên nhân viên")
    public void allocateLeaveMissingEmployeeName() throws Exception {
        allocateLeaveDaysPage.enterEntitlements("10");
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectLeaveTypeIndividual("Nghỉ ốm");
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 3, description = "Phân bổ ngày nghỉ khi để trống số ngày nghỉ")
    public void allocateLeaveMissingEntitlements() throws Exception {
        allocateLeaveDaysPage.enterEmployeeName("Long");
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectLeaveTypeIndividual("Nghỉ ốm");
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 4, description = "Phân bổ ngày nghỉ nhưng để trống loại nghỉ phép")
    public void allocateLeaveMissingLeaveType() throws Exception {
        allocateLeaveDaysPage.enterEmployeeName("Long");
        Thread.sleep(2000);
        allocateLeaveDaysPage.enterEntitlements("10");
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 5, description = "Phân bổ ngày nghỉ với trường Entitlement sai format")
    public void invalidEntitlementFormat() throws Exception {
        allocateLeaveDaysPage.enterEmployeeName("Long");
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectLeaveTypeIndividual("Nghỉ ốm");
        Thread.sleep(2000);
        allocateLeaveDaysPage.enterEntitlements("abc");
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper
                .isErrorMessageDisplayed("Should be a number with upto 2 decimal places"), "Should be a number with upto 2 decimal places");
    }

    @Test(priority = 6, description = "Phân bố ngày nghỉ cho nhiều nhân viên thành công")
    public void allocateLeaveForMultipleEmployeesSuccessfully() throws Exception {
        allocateLeaveDaysPage.selectOptMultiEmployee();
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectSubUnit("HR");
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectLeaveTypeMultiple("Du lịch");
        Thread.sleep(2000);
        allocateLeaveDaysPage.enterEntitlements("10");
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickSaveBtn();
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickConfirmBtn();
    }

    @Test(priority = 7, description = "Phân bổ ngày nghỉ cho nhiều nhân viên khi để trống Sub Unit")
    public void allocateLeaveForMultipleEmployeesMissingSubUnit() throws Exception {
        allocateLeaveDaysPage.selectOptMultiEmployee();
        Thread.sleep(2000);
        allocateLeaveDaysPage.selectLeaveTypeMultiple("Du lịch");
        Thread.sleep(2000);
        allocateLeaveDaysPage.enterEntitlements("10");
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickSaveBtn();
        Thread.sleep(2000);
        allocateLeaveDaysPage.clickConfirmBtn();
        Thread.sleep(2000);
    }

    @Test(priority = 8, description = "Huỷ phân bổ ngày nghỉ phép")
    public void cancelAllocateEntitlements() throws Exception {
        allocateLeaveDaysPage.clickCancelBtn();
        Thread.sleep(2000);
    }
}
