package TestCases;

import Common.BaseSetup;
import Common.UIHelper;
import Pages.AddCandidatePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddCandidateTest extends BaseSetup {

    AddCandidatePage addCandidatePage;
    UIHelper UIHelper;

    @BeforeClass
    public void initPage() {
        addCandidatePage = new AddCandidatePage(driver);
        UIHelper = new UIHelper(driver);
    }

    @BeforeMethod
    public void runRecruitmentTab() throws Exception {
        addCandidatePage.clickTabRecruitment();
        Thread.sleep(2000);
    }

    @Test(priority = 1, description = "Thêm ứng viên thành công")
    public void addCandidateSuccessfully() throws Exception {
        Thread.sleep(2000);
        addCandidatePage.clickBtnAdd();
        Thread.sleep(2000);
        addCandidatePage.fillCandidateForm("B", "Văn", "Nguyễn", "nguyenvanb@gmail.com");
        Thread.sleep(2000);
        addCandidatePage.clickSaveBtn();
        Thread.sleep(3000);
        //Assert.assertTrue(validateFunction.getSuccessToastMessage().contains("Successfully Saved"));
    }
    @Test(priority = 2, description = "Để trống tên khi thêm ứng viên")
    public void addCandidateMissingLastname() throws Exception {
        Thread.sleep(3000);
        addCandidatePage.clickBtnAdd();
        Thread.sleep(2000);
        addCandidatePage.fillCandidateForm("B", "Hoàng", "", "nguyenvanb@gmail.com");
        Thread.sleep(2000);
        addCandidatePage.clickSaveBtn();
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 3, description = "Để trống họ khi thêm ứng viên")
    public void addEmployeeMissingFirstname() throws Exception {
        addCandidatePage.clickBtnAdd();
        Thread.sleep(2000);
        addCandidatePage.fillCandidateForm("", "Hoàng", "Nguyễn", "nguyenvanb@gmail.com");
        Thread.sleep(2000);
        addCandidatePage.clickSaveBtn();
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 4, description = "Để trống email khi thêm ứng viên mới")
    public void addEmployeeMissingEmail() throws Exception {
        addCandidatePage.clickBtnAdd();
        Thread.sleep(2000);
        addCandidatePage.fillCandidateForm("B", "Hoàng", "Nguyễn", "");
        Thread.sleep(2000);
        addCandidatePage.clickSaveBtn();
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 5, description = "Nhập sai định dạng email khi thêm ứng viên")
    public void validateEmailFormat() throws Exception {
        addCandidatePage.clickBtnAdd();
        Thread.sleep(2000);
        addCandidatePage.fillCandidateForm("B", "Hoàng", "Nguyễn", "nguyenvana");
        Thread.sleep(2000);
        addCandidatePage.clickSaveBtn();
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper
                .isErrorMessageDisplayed("Expected format: admin@example.com"), "Expected format: admin@example.com");
    }

    @Test(priority = 6, description = "Huỷ thêm ứng viên")
    public void cancelCandidateAddition() throws Exception {
        addCandidatePage.clickBtnAdd();
        Thread.sleep(2000);
        addCandidatePage.clickCancelBtn();
        Thread.sleep(3000);
    }

    @Test(priority = 7, description = "Xoá ứng viên thành công")
    public void deleteCandidateConfirmYes() throws Exception {
        Thread.sleep(2000);
        addCandidatePage.clickBtnDelete();
        Thread.sleep(3000);
        addCandidatePage.confirmDelete();
        Thread.sleep(2000);
    }

    @Test(priority = 8, description = "Huỷ xoá ứng viên")
    public void deleteCandidateCancelNo() throws Exception {
        Thread.sleep(2000);
        addCandidatePage.clickBtnDelete();
        Thread.sleep(3000);
        addCandidatePage.cancelDelete();
        Thread.sleep(2000);
    }
}