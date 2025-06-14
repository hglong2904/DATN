package TestCases;

import Common.BaseSetup;
import Pages.EditEmployeePage;
import Pages.AddEmployeePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditEmployeeTest extends BaseSetup {

    EditEmployeePage editEmployeePage;
    AddEmployeePage addEmployeePage;

    @BeforeClass
    public void initPage(){
        editEmployeePage = new EditEmployeePage(driver);
        addEmployeePage = new AddEmployeePage(driver);
    }

    @BeforeMethod
    public void runPage(){
        addEmployeePage.clickTabPIM();
    }

    @Test (priority = 1, description = "Cập nhật tên của nhân viên thành công")
    public void updateLastName() throws Exception {
        editEmployeePage.UpdateEmployeeName("Lâm", "Hoàng", "Triệu");
        Assert.assertTrue(UIHelper.getSuccessToastMessage().contains("Successfully Updated"));
    }

    @Test(priority = 2, description = "Cập nhật họ của nhân viên thành công")
    public void updateFirstName() throws Exception {
        editEmployeePage.UpdateEmployeeName("Long", "Hoàng", "Nguyễn");
        Assert.assertTrue(UIHelper.getSuccessToastMessage().contains("Successfully Updated"));
    }

    @Test(priority = 3, description = "Cập nhật ngày sinh của nhân viên")
    public void updateDOB() throws Exception {
        editEmployeePage.enterDateOfBirth("2003-04-29");
        Assert.assertTrue(UIHelper.getSuccessToastMessage().contains("Successfully Updated"));
    }

    @Test(priority = 4, description = "Cập nhật giới tính của nhân viên")
    public void updateGender() throws Exception {
        editEmployeePage.updateGender("Male");
        Assert.assertTrue(UIHelper.getSuccessToastMessage().contains("Successfully Updated"));
    }

    @Test(priority = 5, description = "Cập nhật khi để trống First Name")
    public void updateInfoEmployeeMissingFirstname() throws Exception {
        editEmployeePage.UpdateEmployeeName("", "Hoàng", "Triệu");
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 6, description = "Cập nhật khi để trống Last Name")
    public void updateInfoEmployeeMissingLastname() throws Exception {
        editEmployeePage.UpdateEmployeeName("Long", "Hoàng", "");
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("Required"), "Required");
    }

    @Test(priority = 7, description = "Chọn Yes khi xoá nhân viên")
    public void confirmEmployeeDeletion() throws Exception {
        editEmployeePage.clickBtnDelete(1);
        Thread.sleep(5000);
        editEmployeePage.clickBtnDelete_ConfirmYes();
    }

    @Test(priority = 8, description = "Chọn No khi xoá nhân viên")
    public void cancelEmployeeDeletion() throws Exception {
        editEmployeePage.clickBtnDelete(1);
        Thread.sleep(5000);
        editEmployeePage.clickBtnDelete_ConfirmNo();
    }
}