package TestCases;

import Common.BaseSetup;
import Common.UIHelper;
import Pages.AllocateLeaveDaysPage;
import Pages.SubmitLeaveRequestPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubmitLeaveRequestTest extends BaseSetup {
    SubmitLeaveRequestPage submitLeaveRequestPage;
    UIHelper UIHelper;
    AllocateLeaveDaysPage allocateLeaveDaysPage;

    @BeforeClass
    public void initPage(){
        submitLeaveRequestPage = new SubmitLeaveRequestPage(driver);
        UIHelper = new UIHelper(driver);
        allocateLeaveDaysPage = new AllocateLeaveDaysPage(driver);
    }

    @BeforeMethod
    public void runLeaveTab() throws Exception {
        allocateLeaveDaysPage.clickTabLeave();
        Thread.sleep(2000);
        submitLeaveRequestPage.clickApplyTab();
        Thread.sleep(2000);
    }

    @Test(priority = 1, description = "Gửi yêu cầu nghỉ một ngày thành công")
    public void Submit1dayLeaveRequestSuccessfully() throws Exception{
        submitLeaveRequestPage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterFromDate("2025-05-24");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterToDate("2025-05-24");
        Thread.sleep(2000);
        submitLeaveRequestPage.selectDuration("Full Day");
        Thread.sleep(2000);
        submitLeaveRequestPage.clickApplyBtn();
    }

    @Test(priority = 2, description = "Gửi yêu cầu nghỉ một ngày thiếu loại nghỉ phép")
    public void SubmitLeave1dayRequestMissingLeaveType() throws Exception{
        submitLeaveRequestPage.enterFromDate("2025-05-24");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterToDate("2025-05-24");
        Thread.sleep(2000);
        submitLeaveRequestPage.selectDuration("Full Day");
        Thread.sleep(2000);
        submitLeaveRequestPage.clickApplyBtn();
    }

    @Test(priority = 3, description = "Gửi yêu cầu nghỉ phép thiếu ngày bắt đầu")
    public void SubmitRequestMissingFromDate() throws Exception{
        submitLeaveRequestPage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterToDate("2025-05-25");
        Thread.sleep(2000);
        submitLeaveRequestPage.clickApplyBtn();
    }

    @Test(priority = 4, description = "Gửi yêu cầu nghỉ nhiều ngày thành công")
    public void SubmitMultiDayLeaveRequestSuccessfully() throws Exception{
        submitLeaveRequestPage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterFromDate("2025-05-24");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterToDate("2025-05-26");
        Thread.sleep(2000);
        submitLeaveRequestPage.clickApplyBtn();
    }

    @Test(priority = 5, description = "Gửi yêu cầu nghỉ phép với ngày bắt đầu lớn hơn ngày kết thúc")
    public void FromDateAfterToDate() throws Exception{
        submitLeaveRequestPage.selectLeaveType("Nghỉ ốm");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterFromDate("2025-05-30");
        Thread.sleep(2000);
        submitLeaveRequestPage.enterToDate("2025-05-20");
        Thread.sleep(2000);
        submitLeaveRequestPage.clickApplyBtn();
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("To date should be after from date"), "To date should be after from date");
    }
}
