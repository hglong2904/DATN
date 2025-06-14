package TestCases;

import Pages.AddEmployeePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.SearchEmployeePage;
import Common.BaseSetup;

public class SearchEmployeeTest extends BaseSetup {

    SearchEmployeePage searchEmployeePage;
    AddEmployeePage addEmployeePage;

    @BeforeClass
    public void initPage(){
        searchEmployeePage = new SearchEmployeePage(driver);
        addEmployeePage = new AddEmployeePage(driver);
    }

    @BeforeMethod
    public void runPage(){
        addEmployeePage.clickTabPIM();
    }

    @Test(priority = 1, description = "Tìm kiếm nhân viên thành công")
    public void searchValidEmployeeName() throws Exception {
        searchEmployeePage.searchByEmployeeName("Long Hoàng Triệu");
        Thread.sleep(3000);
    }

    @Test(priority = 2, description = "Tìm kiếm nhân viên không tồn tại")
    public void searchInvalidEmployeeName() throws Exception {
        searchEmployeePage.searchByEmployeeName("Nguyễn Văn A");
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.getNoRecordToastMessage().contains("No Records Found"));
    }

    @Test(priority = 3, description = "Tìm kiếm nhân viên với ID tồn tại")
    public void searchValidEmployeeID() throws Exception {
        searchEmployeePage.searchByEmployeeID("0001");
        Thread.sleep(3000);
    }

    @Test(priority = 4, description = "Tìm kiếm nhân viên với ID không tồn tại")
    public void searchInvalidEmployeeID() throws Exception {
        searchEmployeePage.searchByEmployeeID("E12345");
        Thread.sleep(3000);
        Assert.assertTrue(UIHelper.getNoRecordToastMessage().contains("No Records Found"));
    }

    @Test(priority = 5, description = "Tìm kiếm nhân viên bằng Job Title")
    public void searchByJobTitle() throws Exception {
        searchEmployeePage.selectJobTitle("BA");
    }

    @Test(priority = 6, description = "Tìm kiếm nhân viên bằng Sub Unit")
    public void searchBySubUnit() throws Exception {
        searchEmployeePage.selectSubUnit("HR");
    }
}
