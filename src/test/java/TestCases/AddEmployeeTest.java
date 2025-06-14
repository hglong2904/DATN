package TestCases;

import Common.BaseSetup;
import Common.UIHelper;
import Pages.AddEmployeePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddEmployeeTest extends BaseSetup {

    AddEmployeePage addEmployeePage;
    UIHelper UIHelper;

    @BeforeClass
    public void initPage() {
        addEmployeePage = new AddEmployeePage(driver);
        UIHelper = new UIHelper(driver);
    }

    @BeforeMethod
    public void runRecruitmentTab() throws Exception {
        addEmployeePage.clickTabPIM();
        Thread.sleep(2000);
        addEmployeePage.clickBtnAdd();
        Thread.sleep(2000);
    }

    @Test(priority = 1, description = "Thêm nhân viên thành công")
    public void addEmployeeSuccessfully() throws Exception {
        addEmployeePage.fillEmployeeForm("Long", "Hoàng", "Triệu");
        Thread.sleep(2000);
        addEmployeePage.clickSaveBtn();
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.getSuccessToastMessage().contains("Successfully Saved"));

    }
    @Test(priority = 2, description = "Thêm nhân viên thiếu họ")
    public void addEmployeeMissingLastName() throws Exception {
        addEmployeePage.fillEmployeeForm("Long", "Hoàng", "");
        Thread.sleep(2000);
        addEmployeePage.clickSaveBtn();
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 3, description = "Thêm nhân viên thiếu tên")
    public void addEmployeeMissingFirstName() throws Exception {
        addEmployeePage.fillEmployeeForm("", "Hoàng", "Triệu");
        Thread.sleep(2000);
        addEmployeePage.clickSaveBtn();
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 4, description = "Nhập Employee ID mới")
    public void addNewEmployeeID() throws Exception {
        addEmployeePage.fillEmployeeForm("Long", "Hoàng", "Triệu");
        Thread.sleep(2000);
        addEmployeePage.SetEmployeeID("1236");
        Thread.sleep(1000);
        addEmployeePage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.getSuccessToastMessage().contains("Successfully Saved"));
    }

    @Test(priority = 5, description = "Nhập ID vượt quá số ký tự")
    public void enterIDExceedsCharacters() throws Exception {
        addEmployeePage.fillEmployeeForm("Long", "Hoàng", "Triệu");
        addEmployeePage.SetEmployeeID("123456789123");
        Thread.sleep(1000);
        addEmployeePage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Should not exceed 10 characters"), "Should not exceed 10 characters");
    }

    @Test(priority = 6, description = "Trùng lặp ID")
    public void DuplicateID() throws Exception {
        addEmployeePage.fillEmployeeForm("Long", "Hoàng", "Triệu");
        Thread.sleep(2000);
        addEmployeePage.SetEmployeeID("4568");
        Thread.sleep(1000);
        addEmployeePage.clickSaveBtn();
        Thread.sleep(2000);
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Employee Id already exists"), "Employee Id already exists");
    }

    @Test(priority = 7, description = "Huỷ thêm nhân viên")
    public void cancelAddingEmployee() throws Exception {
        addEmployeePage.clickCancelBtn();
        Thread.sleep(3000);
    }
}