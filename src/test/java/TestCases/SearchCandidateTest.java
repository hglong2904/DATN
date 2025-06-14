package TestCases;

import Common.BaseSetup;
import Pages.AddCandidatePage;
import org.testng.Assert;
import org.testng.annotations.*;
import Pages.SearchCandidatePage;

public class SearchCandidateTest extends BaseSetup {

    SearchCandidatePage searchCandidatePage;
    AddCandidatePage addCandidatePage;

    @BeforeClass
    public void initPage() {
        searchCandidatePage = new SearchCandidatePage(driver);
        addCandidatePage = new AddCandidatePage(driver);
    }

    @BeforeMethod
    public void runRecruitmentTab(){
        addCandidatePage.clickTabRecruitment();
    }

    @Test(priority = 1, description = "Tìm kiếm ứng viên bằng tên thành công")
    public void searchByCandidateNameSuccess() throws Exception {
        searchCandidatePage.enterCandidateName("Triệu");
        Thread.sleep(2000);
        searchCandidatePage.clickSearch();
    }

    @Test(priority = 2, description = "Tìm kiếm ứng viên không tồn tại")
    public void searchByCandidateNameFailure() throws Exception {
        searchCandidatePage.enterCandidateName("Nguyễn Văn B");
        searchCandidatePage.clickSearch();
        Assert.assertTrue(searchCandidatePage.isNameInvalid(), "Invalid");
    }

   @Test(priority = 3, description = "Tìm kiếm ứng viên bằng Keyword không tồn tại")
    public void searchByKeywordsFailure(){
        searchCandidatePage.enterKeywords("Long");
        searchCandidatePage.clickSearch();
        Assert.assertTrue(UIHelper.getNoRecordToastMessage().contains("No Records Found"));
    }

    @Test(priority = 4, description = "Tìm kiếm nhân viên bằng Job Title")
    public void searchByJobTitle(){
        searchCandidatePage.selectJobTitle("BA");
    }

    @Test(priority = 5, description = "Tìm kiếm ứng viên khi ngày bắt đầu lớn hơn ngày kết thúc")
    public void searchByDate() throws Exception{
        searchCandidatePage.enterFromDate("2025-05-20");
        Thread.sleep(2000);
        searchCandidatePage.enterToDate("2025-05-30");
        Thread.sleep(2000);
        searchCandidatePage.clickSearch();
    }

    @Test(priority = 6, description = "Tìm kiếm ứng viên khi ngày bắt đầu lớn hơn ngày kết thúc")
    public void searchWithFromDateAfterToDate() throws Exception{
        searchCandidatePage.enterFromDate("2025-05-30");
        Thread.sleep(2000);
        searchCandidatePage.enterToDate("2025-05-20");
        Thread.sleep(2000);
        searchCandidatePage.clickSearch();
        Assert.assertTrue(UIHelper.isErrorMessageDisplayed("To date should be after from date"), "To date should be after from date");
    }

    @Test(priority = 7, description = "Reset tìm kiếm nhân viên")
    public void resetSearchCandidate() throws Exception{
        searchCandidatePage.enterCandidateName("Nguyễn");
        Thread.sleep(2000);
        searchCandidatePage.clickSearch();
        Thread.sleep(2000);
        searchCandidatePage.clickReset();
    }
}
